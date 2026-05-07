package com.hotcoin.swap_api.util;

import com.alibaba.fastjson.JSON;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.enums.HttpMethodEnum;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * HTTP 请求工具类（基于 Jodd-HTTP，封装签名后的 REST API 调用）
 * HTTP client utility for signed REST API calls, built on top of Jodd-HTTP.
 *
 * <p>所有方法均会先调用 {@link SignatureUtil} 生成签名参数，再发起实际 HTTP 请求。
 * All methods first delegate to {@link SignatureUtil} to generate signed query params,
 * then execute the actual HTTP request.</p>
 *
 * @version V1.0
 * @author hotcoin
 * @date 2022/4/15
 */
public class HttpUtil {

    /**
     * 发送带签名的 POST 请求 / Sends a signed POST request.
     *
     * <p>签名参数附加到 URL 查询串，业务体以 JSON 格式放在请求体中。
     * Signing params are appended as query string; business body is sent as JSON.</p>
     *
     * @param configEnum 全局配置枚举（含 AccessKey, SecretKey, Host）
     *                   / Global config enum (AccessKey, SecretKey, Host)
     * @param apiUri     API 路径 / API URI path
     * @param pathParam  追加到 URL 的额外查询参数 / Extra query params appended to URL
     * @param body       请求体对象（将被序列化为 JSON）/ Request body object (serialized to JSON)
     * @return 服务端响应的原始文本 / Raw response text from the server
     */
    public static String post(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam, Object body) {
        // 生成包含签名的参数 Map / Generate parameter map with signature
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.POST, apiUri);
        // 发送 POST 请求，签名参数作为查询串，body 序列化为 JSON
        // Send POST request: signature as query string, body serialized as JSON
        HttpResponse response = HttpRequest.post(configEnum.getHOST() + apiUri).query(signature)
                .contentType("application/json", "utf-8").body(JSON.toJSONString(body)).send();
        try {
            response.charset("utf-8");
            return response.bodyText(); // 返回响应文本 / Return response body text
        } finally {
            response.close(); // 确保连接被关闭 / Ensure the connection is closed
        }
    }

    /**
     * 发送带签名的 GET 请求 / Sends a signed GET request.
     *
     * @param configEnum 全局配置枚举 / Global config enum
     * @param apiUri     API 路径 / API URI path
     * @param pathParam  请求查询参数 / Request query parameters
     * @return 服务端响应的原始文本 / Raw response text from the server
     */
    public static String get(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam) {
        // 生成签名参数 / Generate signed parameter map
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.GET, apiUri);
        // 发送 GET 请求 / Send GET request
        HttpResponse response =
                HttpRequest.get(configEnum.getHOST() + apiUri).query(signature).contentTypeJson()
                        .send();
        try {
            response.charset("utf-8");
            return response.bodyText(); // 返回响应文本 / Return response body text
        } finally {
            response.close(); // 确保连接被关闭 / Ensure the connection is closed
        }
    }

    /**
     * 使用明文密钥发送带签名的 GET 请求（不通过 GlobalConfigEnum，适合多账号场景）
     * Sends a signed GET request with explicit credentials (bypasses GlobalConfigEnum, useful for multi-account scenarios).
     *
     * @param host      目标 Host 地址，例如 https://api-ct.hotcoin.fit
     *                  / Target host, e.g. https://api-ct.hotcoin.fit
     * @param apiUri    API 路径 / API URI path
     * @param pathParam 请求查询参数 / Request query parameters
     * @param accessKey 明文 AccessKey / Plain-text access key
     * @param secretKey 明文 SecretKey / Plain-text secret key
     * @return 服务端响应的原始文本 / Raw response text from the server
     */
    public static String hostGet(String host, String apiUri, Map<String, String> pathParam,
            String accessKey, String secretKey) {
        // 使用明文密钥生成签名 / Generate signature with explicit keys
        Map<String, String> signature = SignatureUtil
                .createSignature(pathParam, HttpMethodEnum.GET, apiUri, accessKey, secretKey);
        // 发送 GET 请求 / Send GET request
        HttpResponse response =
                HttpRequest.get(host + apiUri).query(signature).contentTypeJson().send();
        try {
            response.charset("utf-8");
            return response.bodyText(); // 返回响应文本 / Return response body text
        } finally {
            response.close(); // 确保连接被关闭 / Ensure the connection is closed
        }
    }

    /**
     * 发送带签名的 DELETE 请求 / Sends a signed DELETE request.
     *
     * @param configEnum 全局配置枚举 / Global config enum
     * @param apiUri     API 路径 / API URI path
     * @param pathParam  追加到 URL 的查询参数 / Query parameters appended to URL
     * @param body       请求体对象（将被序列化为 JSON，可为 null）
     *                   / Request body object (serialized to JSON, may be null)
     * @return 服务端响应的原始文本 / Raw response text from the server
     */
    public static String del(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam, Object body) {
        // 生成签名参数 / Generate signed parameter map
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.DELETE, apiUri);
        // 发送 DELETE 请求，body 序列化为 JSON / Send DELETE request, body serialized as JSON
        HttpResponse response =
                HttpRequest.delete(configEnum.getHOST() + apiUri).query(signature).contentTypeJson()
                        .body(JSON.toJSONString(body)).send();
        try {
            response.charset("utf-8");
            return response.bodyText(); // 返回响应文本 / Return response body text
        } finally {
            response.close(); // 确保连接被关闭 / Ensure the connection is closed
        }
    }

    /**
     * 内部工具方法：将参数 Map 拼接为 URL 查询字符串
     * Internal helper: assembles a URL with sorted query params from a Map.
     *
     * @param host      Host 地址 / Host address
     * @param uri       API 路径 / API path
     * @param pathParam 参数 Map / Parameter map
     * @return 完整 URL 字符串 / Full URL string
     */
    private static String getUrl(
            String host, String uri, Map<String, Object> pathParam) {
        // 将参数按键名排序后拼接为 key=value& 格式 / Sort params by key and join as key=value&...
        String temp =
                pathParam.keySet().stream().sorted().map(key -> key + "=" + pathParam.get(key))
                        .collect(Collectors.joining("&"));
        return host + uri + "?" + temp;
    }
}
