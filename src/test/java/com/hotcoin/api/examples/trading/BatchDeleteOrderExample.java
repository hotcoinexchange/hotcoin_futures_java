package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * 批量撤单示例（撤销指定合约的全部委托）/ Batch cancel all orders for a contract example.
 *
 * <p>演示如何一次性撤销某合约下的所有未成交委托单。
 * Demonstrates how to cancel all open orders for a given contract in a single request.</p>
 *
 * <p>接口 / Endpoint: DELETE /api/v1/perpetual/products/{contractCode}/orders</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchDeleteOrderExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/orders";

    public static void main(String[] args) {

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, new HashMap<>(), null);
        System.out.println(result);
    }
}
