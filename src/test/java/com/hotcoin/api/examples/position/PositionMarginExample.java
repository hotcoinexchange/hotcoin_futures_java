package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改仓位保证金示例 / Change position margin (add or reduce) example.
 *
 * <p>演示如何手动增加或减少指定合约仓位的保证金数量（逐仓模式下有效）。
 * Demonstrates how to manually add or reduce margin for a position
 * in isolated margin mode.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/position/{contractCode}/change-margin</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionMarginExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/change-margin";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();

        /** 调整保证金数量（正数=增加保证金 / 负数=减少保证金）
         *  Margin adjustment amount (positive=add margin, negative=reduce margin) */
        bodyParam.put("margin", "1000");

        /** 仓位方向（long=多仓 / short=空仓）/ Position side (long or short) */
        bodyParam.put("side", "long");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap<>(), bodyParam);
        System.out.println(result);
    }
}
