package com.hotcoin.swap_api.util;

import com.alibaba.fastjson.JSON;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.enums.HttpMethodEnum;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * httpclint工具类
 *
 * @version V1.0
 * @description: TODO 类描述
 * @author: hotcoin
 * @date: 2022/4/15
 **/
public class HttpUtil {

    public static String post(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam, Object body) {
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.POST, apiUri);
        HttpResponse response = HttpRequest.post(configEnum.getHOST() + apiUri).query(signature)
                .contentType("application/json", "utf-8").body(JSON.toJSONString(body)).send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }

    }

    public static String get(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam) {
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.GET, apiUri);
        HttpResponse response =
                HttpRequest.get(configEnum.getHOST() + apiUri).query(signature).contentTypeJson()
                        .send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }
    }


    public static String hostGet(String host, String apiUri, Map<String, String> pathParam,
            String accessKey, String secretKey) {
        Map<String, String> signature = SignatureUtil
                .createSignature(pathParam, HttpMethodEnum.GET, apiUri, accessKey, secretKey);
        HttpResponse response =
                HttpRequest.get(host + apiUri).query(signature).contentTypeJson().send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }
    }

    /**
     * delete请求
     */
    public static String del(GlobalConfigEnum configEnum, String apiUri,
            Map<String, String> pathParam, Object body) {
        Map<String, String> signature =
                SignatureUtil.createSignature(configEnum, pathParam, HttpMethodEnum.DELETE, apiUri);
        HttpResponse response =
                HttpRequest.delete(configEnum.getHOST() + apiUri).query(signature).contentTypeJson()
                        .body(JSON.toJSONString(body)).send();
        try {
            response.charset("utf-8");
            return response.bodyText();
        } finally {
            response.close();
        }
    }

    private static String getUrl(
            String host, String uri, Map<String, Object> pathParam) {
        String temp =
                pathParam.keySet().stream().sorted().map(key -> key + "=" + pathParam.get(key))
                        .collect(Collectors.joining("&"));
        return host + uri + "?" + temp;
    }
}
