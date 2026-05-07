package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单交易详情（成交明细）示例 / Order transaction / fill details example.
 *
 * <p>演示如何查询指定订单的成交明细，包括成交价格、成交数量、手续费等。
 * Demonstrates how to query the fill details of a specific order,
 * including fill price, fill quantity and fees.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/products/{contractCode}/orderDetail</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class OrderDealDetailExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/orderDetail";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 要查询的订单 ID / Order ID to query */
        pathParam.put("orderId", "3253898960830720");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
