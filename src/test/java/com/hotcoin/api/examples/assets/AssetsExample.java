package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询单个合约资产示例 / Query my assets for a single contract example.
 *
 * <p>演示如何查询当前账户在指定永续合约中的资产信息（保证金、可用余额等）。
 * Demonstrates how to query the current account's asset details for a specific
 * perpetual contract (margin, available balance, etc.).</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/account/assets/{contractCode}</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class AssetsExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/account/assets/{0}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
