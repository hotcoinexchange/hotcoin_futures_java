package com.hotcoin.api.utils;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.enums.HttpMethodEnum;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Http工具类
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
public class HttpUtil {

    public static String post(GlobalConfigEnum configEnum, String apiUri, Map<String, String> pathParam, Object body) {
        Map<String, String> signature = SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.POST, apiUri);
        HttpResponse response = HttpRequest.post(configEnum.getHOST() + apiUri)
                .query(signature)
                .contentType("application/json", "utf-8")
                .body(JSON.toJSONString(body))
                .send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }
    }

    public static String get(GlobalConfigEnum configEnum, String apiUri, Map<String, String> pathParam) {
        Map<String, String> signature = SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.GET, apiUri);
        HttpResponse response = HttpRequest.get(configEnum.getHOST() + apiUri)
                .query(signature)
                .contentTypeJson()
                .send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }
    }


    private static String getUrl(String host, String uri, Map<String, Object> pathParam) {
        String temp = pathParam.keySet().stream().sorted().map(key -> key + "=" + pathParam.get(key)).collect(Collectors.joining("&"));
        return host + uri + "?" + temp;
    }

}
