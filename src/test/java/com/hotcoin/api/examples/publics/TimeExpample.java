package com.hotcoin.api.examples.publics;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器时间查询示例 / Server time query example.
 *
 * <p>演示如何获取 Hotcoin 服务器的当前时间，可用于校准本地时钟和生成签名时间戳。
 * Demonstrates how to retrieve the current Hotcoin server time,
 * useful for synchronizing local clocks and generating signing timestamps.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/time</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class TimeExpample {

    /**
     * 请求 URL（无路径参数）/ Request URL (no path parameters).
     */
    static String uriTemplate = "/api/v1/perpetual/public/time";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 无过滤参数，直接返回服务器时间 / No filter — returns server time directly */
        String uri = uriTemplate;

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
