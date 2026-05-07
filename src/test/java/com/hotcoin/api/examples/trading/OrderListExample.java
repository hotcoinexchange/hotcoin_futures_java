package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 当前委托订单列表示例 / Query current open order list example.
 *
 * <p>演示如何查询指定合约的当前挂单列表（未成交的委托）。
 * Demonstrates how to retrieve the list of currently open orders for a given contract.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/products/{contractCode}/list</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class OrderListExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/list";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        // 可根据需要加入过滤参数，例如 type、side 等
        // Additional filter params (e.g. type, side) can be added here if needed.

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
