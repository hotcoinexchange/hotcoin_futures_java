package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 撤销单笔条件单 / 止盈止损单示例 / Cancel a single conditional order (plan / TP / SL) example.
 *
 * <p>演示如何通过合约 CODE 和条件单 ID 撤销一笔计划委托或止盈止损单。
 * Demonstrates how to cancel a single plan order, take-profit or stop-loss order
 * by contract code and conditional order ID.</p>
 *
 * <p>接口 / Endpoint: DELETE /api/v1/perpetual/products/{contractCode}/order/condition/{conditionOrderId}</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class DeleteOrderConditionExample {

    /**
     * 请求 URL 模版（{0} 合约 CODE，{1} 条件单 ID）
     * Request URL template ({0} = contract code, {1} = conditional order ID).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order/condition/{1}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE 和条件单 ID（内嵌在 URI 中）
         *  Path params: contract code and conditional order ID (embedded in the URI) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");

        /** 调用 API / Call the API */
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, pathParam, new HashMap<>());
        System.out.println(result);
    }
}
