package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 撤单示例（取消单笔委托）/ Cancel a single order example.
 *
 * <p>演示如何通过合约 CODE 和订单 ID 撤销一笔未成交的委托单。
 * Demonstrates how to cancel a single open order by contract code and order ID.</p>
 *
 * <p>接口 / Endpoint: DELETE /api/v1/perpetual/products/{contractCode}/order/{orderId}</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class DeleteOrderExample {

    /**
     * 请求 URL 模版（{0} 合约 CODE，{1} 订单 ID）
     * Request URL template ({0} = contract code, {1} = order ID).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order/{1}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE 和订单 ID（内嵌在 URI 中）
         *  Path params: contract code and order ID (embedded in the URI) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");

        /** 调用 API / Call the API */
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, pathParam, new HashMap<>());
        System.out.println(result);
    }
}
