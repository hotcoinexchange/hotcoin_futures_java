package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量撤销条件单 / 止盈止损单示例 / Batch cancel conditional orders (plan / TP / SL) example.
 *
 * <p>演示如何通过提供条件单 ID 列表，批量撤销止盈止损或计划委托单。
 * Demonstrates how to batch-cancel plan orders, take-profit or stop-loss orders
 * by providing a list of conditional order IDs.</p>
 *
 * <p>接口 / Endpoint: DELETE /api/v1/perpetual/products/{contractCode}/orders/condition</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchDeleteOrderConditionExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/orders/condition";

    public static void main(String[] args) {
        // 构建待撤销的条件单 ID 列表 / Build the list of conditional order IDs to cancel
        List<Long> ids = new ArrayList<>();
        ids.add(3254001157243472L); // 待撤销条件单 ID / Conditional order ID to cancel

        /** 路径参数：合约 CODE（{1} 占位符在此接口中未使用）
         *  Path param: contract code ({1} placeholder is not used for this endpoint) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");

        /** 调用 API，将条件单 ID 列表作为请求体发送 / Call the API, sending condition order ID list as request body */
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, new HashMap<>(), ids);
        System.out.println(result);
    }
}
