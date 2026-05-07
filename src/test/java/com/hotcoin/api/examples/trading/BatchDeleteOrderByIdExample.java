package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 按订单 ID 批量撤单示例 / Batch cancel orders by order ID list example.
 *
 * <p>演示如何通过提供订单 ID 列表，批量撤销指定的委托单。
 * Demonstrates how to cancel a specified list of orders by providing their order IDs.</p>
 *
 * <p>接口 / Endpoint: DELETE /api/v1/perpetual/products/{contractCode}/batch-delete-order</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchDeleteOrderByIdExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/batch-delete-order";

    public static void main(String[] args) {
        // 构建需要撤销的订单 ID 列表 / Build the list of order IDs to cancel
        List<Long> ids = new ArrayList<>();
        ids.add(3254001157243472L); // 待撤销订单 ID / Order ID to be cancelled

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API，将订单 ID 列表作为请求体发送 / Call the API, sending order ID list as request body */
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, new HashMap<>(), ids);
        System.out.println(result);
    }
}
