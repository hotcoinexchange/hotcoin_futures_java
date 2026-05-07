package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 指数价格成分信息查询示例 / Index price component info query example.
 *
 * <p>演示如何查询指定合约的指数价格构成信息，包含各交易所的价格权重等。
 * Demonstrates how to retrieve the index price composition for a given contract,
 * including the price and weight of each constituent exchange.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/{contractCode}/indexInfo</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class IndexInfoExpample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/indexInfo";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
