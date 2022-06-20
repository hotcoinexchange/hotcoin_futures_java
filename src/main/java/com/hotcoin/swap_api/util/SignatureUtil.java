package com.hotcoin.swap_api.util;

import org.apache.commons.codec.binary.Base64;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.enums.HttpMethodEnum;

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
 * @version V1.0
 * @description: TODO 类描述
 * @author: hotcoin
 * @date: 2022/4/15
 **/
public class SignatureUtil {
    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");

    /***
     *
     * @param configEnum AK/SK 相关配置
     * @param pathParam URL 参数
     * @param methodEnum Http 方法
     * @param apiUri API URI
     * @return Signature
     */
    public static Map<String, String> createSignature(GlobalConfigEnum configEnum, Map<String, String> pathParam, HttpMethodEnum methodEnum, String apiUri) {
        Map<String, String> paramsToSign = new HashMap<>();
        paramsToSign.put("AccessKeyId", configEnum.getACCESS_KEY());
        paramsToSign.put("SignatureMethod", configEnum.getAlgorithm());
        paramsToSign.put("SignatureVersion", "2");
        paramsToSign.put("Timestamp", gmtUTCTime());
        paramsToSign.putAll(pathParam);
        String temp = paramsToSign.keySet().stream().sorted().map(key -> key + "=" + paramsToSign.get(key)).collect(Collectors.joining("&")).replaceAll(":","%3A");
        String host = "perpetual.hotcoinex.io";
        String payload = String.join("\n", methodEnum.getMethod(), host, apiUri, temp);
        SecretKeySpec secKey = new SecretKeySpec(configEnum.getSECRET_KEY().getBytes(StandardCharsets.UTF_8), configEnum.getAlgorithm());
        try {
            Mac mac = Mac.getInstance(configEnum.getAlgorithm());
            mac.init(secKey);
            byte[] bytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String signature = Base64.encodeBase64String(bytes);
            paramsToSign.put("Signature", signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return paramsToSign;
    }


    /**
     * 生成UTC时间
     *
     * @return
     */
    private static String gmtUTCTime() {
        return Instant.now().atZone(ZONE_GMT).format(DT_FORMAT);
    }
}
