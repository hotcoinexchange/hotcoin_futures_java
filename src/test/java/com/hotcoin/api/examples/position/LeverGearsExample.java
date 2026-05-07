package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取杠杆档位信息示例 / Get lever gear tiers (margin rate and leverage per tier) example.
 *
 * <p>演示如何查询指定合约所有杠杆档位对应的保证金率和最大杠杆倍数。
 * Demonstrates how to retrieve all leverage tier configurations (margin rate and max leverage)
 * for a given perpetual contract.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/{contractCode}/lever-gears</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class LeverGearsExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/lever-gears";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
