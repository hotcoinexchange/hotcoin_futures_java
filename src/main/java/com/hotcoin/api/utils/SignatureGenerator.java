package com.hotcoin.api.utils;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.constant.ApiConstants;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.exceptions.HotcoinApiException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * REST 接口签名生成器（支持 REST 和 WebSocket 两种签名场景）
 * REST API signature generator supporting both REST and WebSocket signing scenarios.
 *
 * <p>REST 签名流程 / REST signing flow:
 * <ol>
 *   <li>验证 AccessKey 和 SecretKey 非空 / Validate that AccessKey and SecretKey are not blank</li>
 *   <li>拼接 HTTP方法、Host、URI 为签名字符串前缀
 *       / Append HTTP method, lowercase host and URI as prefix of the signing string</li>
 *   <li>将 AccessKeyId、SignatureMethod、SignatureVersion、Timestamp 写入 UrlBuilder
 *       / Add AccessKeyId, SignatureMethod, SignatureVersion, Timestamp to UrlBuilder</li>
 *   <li>调用 {@link UrlBuilder#buildSignature()} 得到排序后的查询串
 *       / Call {@link UrlBuilder#buildSignature()} to get the sorted query string</li>
 *   <li>HmacSHA256 + Base64 得到签名，写回 UrlBuilder
 *       / Apply HmacSHA256 + Base64 to get signature and write it back to UrlBuilder</li>
 * </ol>
 *
 * <p>WebSocket 签名流程 / WebSocket signing flow:
 * <ol>
 *   <li>构造包含 AccessKeyId、SignatureMethod、SignatureVersion、Timestamp 的有序参数 Map
 *       / Build an ordered param map with AccessKeyId, SignatureMethod, SignatureVersion, Timestamp</li>
 *   <li>URL 编码后按键值对格式拼接 / URL-encode and join as key=value& string</li>
 *   <li>以 WSS\nhost\n/wss\n 为前缀构造 payload
 *       / Prefix with WSS\nhost\n/wss\n to build the payload</li>
 *   <li>HmacSHA256 + Base64 得到签名 / Compute HmacSHA256 + Base64 for the signature</li>
 * </ol>
 *
 * @author hotcoin
 * @version 1.0.0
 * @date 2022/3/26 10:36
 */
public class SignatureGenerator {

    /** 签名参数字段名常量 / Signing parameter field name constants */
    private static final String ACCESS_KEY_ID = "AccessKeyId";
    private static final String SIGNATURE_METHOD = "SignatureMethod";
    private static final String HMACSHA_256 = "HmacSHA256";
    private static final String SIGNATURE_VERSION = "SignatureVersion";
    private static final String SIGNATURE_VERSION_2 = "2";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";

    /** UTC 时间格式化器 / UTC timestamp formatter */
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /** UTC 时区 / UTC time zone */
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    /**
     * 为 REST 请求生成签名，并将 Signature 写入 UrlBuilder
     * Generates a REST request signature and writes it back into the provided UrlBuilder.
     *
     * <p>签名结果通过 {@link UrlBuilder#putToUrl(String, String)} 写回，调用方可直接从 builder 获取完整请求 URL。
     * The signature is written back via {@link UrlBuilder#putToUrl}, so the caller can build the
     * full request URL directly from the UrlBuilder.</p>
     *
     * @param config  包含 accessKey / secretKey 的 API 配置对象
     *                / API configuration object holding accessKey and secretKey
     * @param method  HTTP 方法字符串（如 GET、POST、DELETE）/ HTTP method string (e.g. GET, POST, DELETE)
     * @param uri     API URI 路径 / API URI path
     * @param builder URL 构建器（同时承载查询参数和签名）/ URL builder holding query params and signature
     * @throws HotcoinApiException 若 accessKey / secretKey 为空或算法不支持或密钥无效
     *                             / If accessKey/secretKey is blank, algorithm is unsupported, or key is invalid
     */
    public static void createSignature(APIConfiguration config, String method,
                                       String uri, UrlBuilder builder) {
        // 构造待签名字符串的前缀部分 / Build the prefix portion of the signing string
        StringBuilder sb = new StringBuilder(1024);

        // 校验 AccessKey 和 SecretKey 非空 / Validate that AccessKey and SecretKey are not blank
        if (StringUtils.isBlank(config.getAccessKey()) || StringUtils.isBlank(config.getSecretKey())) {
            throw new HotcoinApiException(HotcoinApiException.APIKEY_ERROR,
                    "API key and secret key are required");
        }

        // 第1行：HTTP 方法（大写）/ Line 1: HTTP method (uppercase)
        sb.append(method.toUpperCase()).append(ApiConstants.LINE_BREAK)
                // 第2行：Host（小写）/ Line 2: host (lowercase)
                .append(ApiConstants.SIGN_HOST.toLowerCase()).append(ApiConstants.LINE_BREAK)
                // 第3行：URI 路径 / Line 3: URI path
                .append(uri).append(ApiConstants.LINE_BREAK);

        // 将标准签名字段写入 UrlBuilder / Add standard signing fields to UrlBuilder
        builder.putToUrl(ACCESS_KEY_ID, config.getAccessKey())      // AccessKeyId
                .putToUrl(SIGNATURE_METHOD, HMACSHA_256)             // 签名算法 / Signing algorithm
                .putToUrl(SIGNATURE_VERSION, SIGNATURE_VERSION_2)    // 签名版本 / Signature version
                .putToUrl(TIMESTAMP, gmtUTCTime());                  // UTC 时间戳 / UTC timestamp

        // 第4行：排序后的参数查询串 / Line 4: sorted parameter query string
        sb.append(builder.buildSignature());

        // HmacSHA256 签名运算 / HmacSHA256 signing computation
        byte[] hmacSha256;
        try {
            SecretKeySpec secKey = new SecretKeySpec(
                    config.getSecretKey().getBytes(StandardCharsets.UTF_8), HMACSHA_256);
            Mac mac = Mac.getInstance(HMACSHA_256);
            mac.init(secKey);
            // 对完整 payload 进行 HMAC 运算 / Apply HMAC to the full payload string
            hmacSha256 = mac.doFinal(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] Invalid key: " + e.getMessage());
        }

        // Base64 编码并移除换行符，写入 UrlBuilder / Base64-encode, strip newlines, write to UrlBuilder
        builder.putToUrl(SIGNATURE, Base64.encodeBase64String(hmacSha256)
                .replace(ApiConstants.LINE_BREAK, ApiConstants.EMPTY));
    }

    /**
     * 获取当前 UTC 时间字符串（用于签名 Timestamp 字段）
     * Returns the current UTC timestamp string for use as the Timestamp signing field.
     *
     * @return 格式为 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 的 UTC 时间字符串
     *         / UTC time string in format yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    private static String gmtUTCTime() {
        return Instant.now().atZone(ZONE_GMT).format(DT_FORMAT);
    }

    /**
     * 为 WebSocket 连接生成登录签名字符串
     * Generates a sign-in signature string for a WebSocket connection.
     *
     * <p>构造有序参数 Map，URL 编码后拼接为查询串，以 WSS\nhost\n/wss\n 为前缀，
     * 再进行 HmacSHA256 + Base64 计算得到签名值。
     * Builds an ordered param map, URL-encodes it into a query string, prepends
     * WSS\nhost\n/wss\n, then computes HmacSHA256 + Base64 for the signature.</p>
     *
     * @param time      当前时间戳（毫秒），作为 Timestamp 参数
     *                  / Current timestamp in milliseconds, used as the Timestamp param
     * @param accessKey 明文 AccessKey / Plain-text access key
     * @param secretKey 明文 SecretKey（用于 HMAC 签名）/ Plain-text secret key (for HMAC signing)
     * @return Base64 编码的签名字符串 / Base64-encoded signature string
     * @throws HotcoinApiException 若签名运算出错 / If a signing error occurs
     */
    public static String createWebSocketSignature(long time, String accessKey, String secretKey) {
        try {
            // WebSocket 签名所用的 Host 和伪 HTTP 方法 / Host and pseudo-method for WebSocket signing
            String host = "api.ws.contract.hotcoin.top";
            String method = "WSS";

            // 构造有序参数 Map（TreeMap 保证键名字典序）
            // Build ordered parameter map (TreeMap ensures alphabetical key order)
            SortedMap<String, String> sortedParams = new TreeMap<>();
            sortedParams.put("AccessKeyId", accessKey);              // 访问密钥 ID / Access key ID
            sortedParams.put("SignatureMethod", "HmacSHA256");       // 签名算法 / Signing algorithm
            sortedParams.put("SignatureVersion", "2");                // 签名版本 / Signature version
            sortedParams.put("Timestamp", Long.toString(time));      // 毫秒时间戳 / Millisecond timestamp

            // URL 编码后将键值对拼接为 key=value& 格式
            // URL-encode each key/value and join as key=value& string
            StringBuilder tempParams = new StringBuilder();
            for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
                if (tempParams.length() > 0) {
                    tempParams.append("&");
                }
                tempParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
                tempParams.append("=");
                tempParams.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            }

            // 构造完整 payload：WSS\nHost\n/wss\n<排序参数串>
            // Build full payload: WSS\nhost\n/wss\n<sorted_params>
            String payload = method + "\n" + host + "\n/" + "wss" + "\n" + tempParams;

            // HmacSHA256 签名运算 / Apply HmacSHA256
            SecretKeySpec secKey = new SecretKeySpec(
                    secretKey.getBytes(StandardCharsets.UTF_8), HMACSHA_256);
            Mac mac = Mac.getInstance(HMACSHA_256);
            mac.init(secKey);
            byte[] hmacSha256 = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            // Base64 编码并移除换行符 / Base64-encode and strip newlines
            return Base64.encodeBase64String(hmacSha256)
                    .replace(ApiConstants.LINE_BREAK, ApiConstants.EMPTY);

        } catch (NoSuchAlgorithmException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR,
                    "[Signature] Invalid key: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
