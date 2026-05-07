package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置杠杆示例 / Set leverage example.
 *
 * <p>演示如何为指定合约的多仓或空仓调整杠杆倍数和保证金模式（全仓/逐仓）。
 * Demonstrates how to adjust the leverage multiplier and margin mode
 * (cross margin or isolated margin) for a long or short position.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/position/{contractCode}/lever</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionLeverExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/lever";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();

        /** 保证金模式（0=全仓 cross margin / 1=逐仓 isolated margin）
         *  Margin type (0=cross margin, 1=isolated margin) */
        bodyParam.put("type", "1");

        /** 杠杆倍数（有效范围 1~125）/ Leverage multiplier (valid range: 1~125) */
        bodyParam.put("lever", "2");

        /** 仓位方向（long=多仓 / short=空仓）/ Position side (long or short) */
        bodyParam.put("side", "short");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap<>(), bodyParam);
        System.out.println(result);
    }
}
