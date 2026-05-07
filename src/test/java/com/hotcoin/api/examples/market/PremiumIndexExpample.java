package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 标记价格和资金费率查询示例（溢价指数）/ Mark price and funding rate query example (premium index).
 *
 * <p>演示如何查询指定合约的最新标记价格、指数价格以及当前资金费率（溢价指数）。
 * Demonstrates how to retrieve the latest mark price, index price and current
 * funding rate (premium index) for a given contract.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/{contractCode}/premiumIndex</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class PremiumIndexExpample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/premiumIndex";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
