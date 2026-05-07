package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 合约详细信息列表示例（CoinGecko 格式）/ Contract info list example (CoinGecko-compatible format).
 *
 * <p>演示如何查询平台所有永续合约的详细配置信息，返回格式兼容 CoinGecko 交易所 API 规范。
 * Demonstrates how to retrieve detailed configuration for all perpetual contracts,
 * returned in a format compatible with the CoinGecko Exchange API specification.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/contracts</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class PublicContractsExpample {

    /**
     * 请求 URL（无路径参数）/ Request URL (no path parameters).
     */
    static String uriTemplate = "/api/v1/perpetual/public/contracts";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 无过滤参数，返回全部合约信息 / No filter — returns all contract information */
        String uri = uriTemplate;

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
