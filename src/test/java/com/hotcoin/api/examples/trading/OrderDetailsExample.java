package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据订单 ID 查询订单详情示例 / Query order details by order ID example.
 *
 * <p>演示如何通过合约 CODE 和订单 ID 查询单笔订单的详细信息。
 * Demonstrates how to query the details of a single order by contract code and order ID.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/products/{contractCode}/{orderId}</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class OrderDetailsExample {

    /**
     * 请求 URL 模版（{0} 合约 CODE，{1} 订单 ID）
     * Request URL template ({0} = contract code, {1} = order ID).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/{1}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE 和订单 ID（内嵌在 URI 中）
         *  Path params: contract code and order ID (embedded in the URI) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
