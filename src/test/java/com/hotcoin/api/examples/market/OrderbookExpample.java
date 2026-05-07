package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单簿（深度）数据查询示例 / Order book (market depth) data query example.
 *
 * <p>演示如何查询指定合约的买卖盘挂单深度信息。
 * Demonstrates how to retrieve the order book (bid/ask depth) for a given contract.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/products/{contractCode}/orderbook</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class OrderbookExpample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/public/products/{0}/orderbook";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
