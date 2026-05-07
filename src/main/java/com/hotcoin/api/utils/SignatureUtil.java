package com.hotcoin.api.utils;

import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.enums.HttpMethodEnum;
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
 * 签名工具类（旧版，供 com.hotcoin.api 包下的示例代码使用）
 * Signature utility class (legacy version, used by examples in the com.hotcoin.api package).
 *
 * <p>通过 HmacSHA256 算法对请求参数进行签名，并返回包含 Signature 字段的参数 Map。
 * Signs request parameters using HmacSHA256 and returns a parameter map that includes the Signature field.</p>
 *
 * <p>签名步骤 / Signing steps:
 * <ol>
 *   <li>加入 AccessKeyId、SignatureMethod、SignatureVersion、Timestamp 等标准字段
 *       / Add standard fields: AccessKeyId, SignatureMethod, SignatureVersion, Timestamp</li>
 *   <li>合并业务参数，按键名字典序排序 / Merge business params and sort keys alphabetically</li>
 *   <li>构造 payload：HTTP方法\nHost\nURI\n排序后参数串
 *       / Build payload: HTTP_METHOD\nhost\nuri\nsorted_params</li>
 *   <li>HmacSHA256 + Base64 编码得到签名值 / Compute HmacSHA256 then Base64-encode for the signature</li>
 * </ol>
 *
 * @author hotcoin
 * @version 1.0.0
 * @date 2022/3/26 10:36
 */
public class SignatureUtil {

    /** UTC 时间格式化器（签名时间戳）/ UTC timestamp formatter used for signing */
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /** UTC 时区 / UTC time zone */
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    /**
     * 根据 GlobalConfigEnum 生成签名参数（从枚举读取 AccessKey / SecretKey）
     * Generates signed request parameters using credentials stored in GlobalConfigEnum.
     *
     * @param configEnum AK/SK 相关配置枚举 / Config enum holding access key, secret key and algorithm
     * @param pathParam  业务 URL 参数（将与签名参数合并）/ Business URL parameters (merged with signing params)
     * @param methodEnum HTTP 方法枚举（GET / POST / DELETE）/ HTTP method enum
     * @param apiUri     API URI 路径，例如 /api/v1/perpetual/...
     *                   / API URI path, e.g. /api/v1/perpetual/...
     * @return 包含 Signature 在内的完整请求参数 Map / Complete parameter map including the Signature field
     */
    public static Map<String, String> createSignature(GlobalConfigEnum configEnum, Map<String, String> pathParam, HttpMethodEnum methodEnum, String apiUri) {
        // 初始化签名参数 Map，加入认证必须字段 / Initialize signing map with mandatory auth fields
        Map<String, String> paramsToSign = new HashMap<>();
        paramsToSign.put("AccessKeyId", configEnum.getACCESS_KEY());      // 访问密钥 ID / Access key ID
        paramsToSign.put("SignatureMethod", configEnum.getAlgorithm());   // 签名算法 / Signing algorithm
        paramsToSign.put("SignatureVersion", "2");                         // 签名版本号 / Signature version
        paramsToSign.put("Timestamp", gmtUTCTime());                      // 当前 UTC 时间戳 / Current UTC timestamp

        // 合并业务请求参数 / Merge business request parameters
        paramsToSign.putAll(pathParam);

        // 参数按键名字典序排序并拼接为 key=value& 串，冒号替换为 %3A
        // Sort params alphabetically, join as key=value& string, URL-encode colons
        String temp = paramsToSign.keySet().stream().sorted()
                .map(key -> key + "=" + paramsToSign.get(key))
                .collect(Collectors.joining("&"))
                .replaceAll(":", "%3A");

        // 构造待签名 payload：HTTP方法\nHost\nURI\n参数串
        // Build signing payload: HTTP_METHOD\nhost\nuri\nsorted_params
        String host = "perpetual.hotcoinex.io";
        String payload = String.join("\n", methodEnum.getMethod(), host, apiUri, temp);

        // 使用 SecretKey 初始化 HmacSHA256 / Initialize HmacSHA256 with SecretKey
        SecretKeySpec secKey = new SecretKeySpec(
                configEnum.getSECRET_KEY().getBytes(StandardCharsets.UTF_8),
                configEnum.getAlgorithm());
        try {
            Mac mac = Mac.getInstance(configEnum.getAlgorithm());
            mac.init(secKey);
            // 计算 HMAC 并 Base64 编码 / Compute HMAC and Base64-encode the result
            byte[] bytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.encodeBase64String(bytes);
            // 将签名写入参数 Map / Insert signature into parameter map
            paramsToSign.put("Signature", signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return paramsToSign;
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
}
