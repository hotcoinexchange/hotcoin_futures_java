package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询所有合约资产列表示例 / Query all contract assets list example.
 *
 * <p>演示如何查询当前账户在所有永续合约中的资产汇总信息。
 * Demonstrates how to retrieve a summary of the current account's assets
 * across all perpetual contracts.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/account/assets</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class AssetsListExample {

    /**
     * 请求 URL（无路径参数）/ Request URL (no path parameters).
     */
    static String uriTemplate = "/api/v1/perpetual/account/assets";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 无合约 CODE 过滤，返回全部合约资产 / No contract code filter — returns assets for all contracts */
        String uri = uriTemplate;

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
