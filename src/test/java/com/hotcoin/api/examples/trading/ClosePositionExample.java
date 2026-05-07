package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 市价全平仓位示例 / Close a position at market price example.
 *
 * <p>演示如何对指定合约的多仓或空仓执行市价全平操作。
 * Demonstrates how to close a long or short position at market price for a given contract.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/products/{contractCode}/{side}/closePosition</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class ClosePositionExample {

    /**
     * 请求 URL 模版（{0} 合约 CODE，{1} 方向）
     * Request URL template ({0} = contract code, {1} = side).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/{1}/closePosition";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE 和仓位方向（long 多仓 / short 空仓）
         *  Path params: contract code and position side (long or short) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "long");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, pathParam, new HashMap<>());
        System.out.println(result);
    }
}
