package com.hotcoin.swap_api.util;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.enums.HttpMethodEnum;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 签名工具类（使用 HmacSHA256 算法对 REST 请求进行签名认证）
 * Signature utility for HmacSHA256 request signing of Hotcoin Perpetual Futures REST API.
 *
 * <p>签名流程 / Signing steps:
 * <ol>
 *   <li>收集请求参数（AccessKeyId, SignatureMethod, SignatureVersion, Timestamp 等）
 *       / Collect request parameters (AccessKeyId, SignatureMethod, SignatureVersion, Timestamp, etc.)</li>
 *   <li>将参数键按字典序排序后拼接为查询字符串
 *       / Sort parameter keys alphabetically and join as a query string</li>
 *   <li>拼接 HTTP 方法、Host、URI 与查询字符串，构造待签名字符串
 *       / Concatenate HTTP method, host, URI and query string to build the payload</li>
 *   <li>使用 SecretKey 进行 HmacSHA256 运算，并对结果 Base64 编码
 *       / Apply HmacSHA256 with the SecretKey and Base64-encode the result</li>
 *   <li>将 Signature 加入请求参数并返回
 *       / Append the Signature to the parameter map and return</li>
 * </ol>
 *
 * @version V1.0
 * @author hotcoin
 * @date 2022/4/15
 */
public class SignatureUtil {

    /** UTC 时间格式（签名时间戳）/ UTC timestamp format used for signing */
    private static final DateTimeFormatter DT_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /** UTC 时区 / UTC time zone */
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    /**
     * 生成已认证接口的签名参数（从 GlobalConfigEnum 读取 AccessKey / SecretKey）
     * Generates signed params for authenticated endpoints using credentials from GlobalConfigEnum.
     *
     * @param configEnum 全局配置枚举（含 AccessKey, SecretKey, Host, 算法）
     *                   / Global config enum holding AccessKey, SecretKey, Host and algorithm
     * @param pathParam  业务请求参数（将与签名参数合并）/ Business request params (merged with signing params)
     * @param methodEnum HTTP 方法枚举（GET / POST / DELETE）/ HTTP method enum
     * @param apiUri     API 路径，例如 /api/v1/perpetual/... / API path, e.g. /api/v1/perpetual/...
     * @return 包含 Signature 在内的完整请求参数 Map / Complete parameter map including Signature
     */
    public static Map<String, String> createSignature(GlobalConfigEnum configEnum,
            Map<String, String> pathParam, HttpMethodEnum methodEnum, String apiUri) {
        // 初始化签名参数 Map，加入必要的认证字段 / Initialize signing map with mandatory auth fields
        Map<String, String> paramsToSign = new HashMap<>();
        paramsToSign.put("AccessKeyId", configEnum.getACCESS_KEY());        // 访问密钥 ID / Access key ID
        paramsToSign.put("SignatureMethod", configEnum.getAlgorithm());     // 签名算法，如 HmacSHA256 / Signing algorithm e.g. HmacSHA256
        paramsToSign.put("SignatureVersion", "2");                           // 签名版本 / Signature version
        paramsToSign.put("Timestamp", gmtUTCTime());                        // 当前 UTC 时间戳 / Current UTC timestamp
        // 合并业务请求参数 / Merge business request parameters
        paramsToSign.putAll(pathParam);

        // 将参数按键名字典序排序并拼接为 key=value&... 字符串，同时对冒号 URL 编码
        // Sort params alphabetically, join as key=value& string, URL-encode colons
        String temp = paramsToSign.keySet().stream().sorted()
                .map(key -> key + "=" + paramsToSign.get(key)).collect(Collectors.joining("&"))
                .replaceAll(":", "%3A");

        // 构造待签名的 Payload：HTTP方法\nHost\nURI\n参数字符串
        // Build signing payload: HTTP_METHOD\nhost\nuri\nparams
        String host = "perpetual.hotcoinex.io";
        String payload = String.join("\n", methodEnum.getMethod(), host, apiUri, temp);

        // 使用 SecretKey 初始化 HmacSHA256 / Initialize HmacSHA256 with SecretKey
        SecretKeySpec secKey =
                new SecretKeySpec(configEnum.getSECRET_KEY().getBytes(StandardCharsets.UTF_8),
                        configEnum.getAlgorithm());
        try {
            Mac mac = Mac.getInstance(configEnum.getAlgorithm());
            mac.init(secKey);
            // 对 payload 进行 HMAC 运算并 Base64 编码 / Apply HMAC and Base64-encode the result
            byte[] bytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.encodeBase64String(bytes);
            // 将签名写入参数 Map / Put the computed signature into the parameter map
            paramsToSign.put("Signature", signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return paramsToSign;
    }

    /**
     * 使用明文密钥生成签名（AccessKey / SecretKey 作为参数直接传入）
     * Generates signed params with explicit key/secret passed as arguments (no enum required).
     *
     * @param pathParam  业务请求参数 / Business request parameters
     * @param methodEnum HTTP 方法枚举 / HTTP method enum
     * @param apiUri     API 路径 / API URI path
     * @param accessKey  明文 AccessKey / Plain-text access key
     * @param secretKey  明文 SecretKey / Plain-text secret key
     * @return 包含 Signature 在内的完整请求参数 Map / Complete parameter map including Signature
     */
    public static Map<String, String> createSignature(Map<String, String> pathParam,
            HttpMethodEnum methodEnum, String apiUri, String accessKey, String secretKey) {
        // 初始化签名参数 Map / Initialize signing parameter map
        Map<String, String> paramsToSign = new HashMap<>();
        paramsToSign.put("AccessKeyId", accessKey);                 // 访问密钥 ID / Access key ID
        paramsToSign.put("SignatureMethod", "HmacSHA256");          // 签名算法 / Signing algorithm
        paramsToSign.put("SignatureVersion", "2");                   // 签名版本 / Signature version
        paramsToSign.put("Timestamp", gmtUTCTime());                // 当前 UTC 时间戳 / Current UTC timestamp
        // 合并业务请求参数 / Merge business request parameters
        paramsToSign.putAll(pathParam);

        // 参数排序并拼接，冒号 URL 编码 / Sort, join, and URL-encode colons
        String temp = paramsToSign.keySet().stream().sorted()
                .map(key -> key + "=" + paramsToSign.get(key)).collect(Collectors.joining("&"))
                .replaceAll(":", "%3A");

        // 构造待签名字符串 / Build the payload string for signing
        String host = "perpetual.hotcoinex.io";
        String payload = String.join("\n", methodEnum.getMethod(), host, apiUri, temp);

        // 使用 SecretKey 进行 HmacSHA256 计算 / Compute HmacSHA256 with SecretKey
        SecretKeySpec secKey =
                new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secKey);
            // Base64 编码签名结果 / Base64-encode the HMAC result
            byte[] bytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.encodeBase64String(bytes);
            // 将签名加入参数 Map / Insert signature into parameter map
            paramsToSign.put("Signature", signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return paramsToSign;
    }

    /**
     * 获取当前 UTC 时间字符串（用于签名 Timestamp 字段）
     * Returns current UTC timestamp string for use as the Timestamp signing field.
     *
     * @return 格式为 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 的 UTC 时间字符串
     *         / UTC time string in format yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    private static String gmtUTCTime() {
        return Instant.now().atZone(ZONE_GMT).format(DT_FORMAT);
    }
}
