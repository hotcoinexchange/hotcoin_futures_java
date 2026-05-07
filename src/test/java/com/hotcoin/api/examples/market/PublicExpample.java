package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 可用合约列表示例 / Available (tradeable) contracts list example.
 *
 * <p>演示如何查询当前平台所有可交易的永续合约列表。
 * Demonstrates how to retrieve the list of all currently tradeable perpetual contracts
 * on the Hotcoin platform.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class PublicExpample {

    /**
     * 请求 URL（无路径参数）/ Request URL (no path parameters).
     */
    static String uriTemplate = "/api/v1/perpetual/public";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 无过滤参数，返回全部可用合约 / No filter — returns all available contracts */
        String uri = uriTemplate;

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
