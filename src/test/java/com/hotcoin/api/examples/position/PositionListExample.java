package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 仓位列表示例 / Position list example.
 *
 * <p>演示如何查询指定合约的当前仓位信息（包含多仓和空仓）。
 * Demonstrates how to query the current position details for a given contract
 * (including both long and short positions).</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/position/{contractCode}/configs</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionListExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/configs";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
