package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 普通单下单示例 / Place a regular order example.
 *
 * <p>演示如何通过 REST API 提交一笔限价或市价委托单。
 * Demonstrates how to submit a limit or market order via the REST API.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/products/{contractCode}/order</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class OrderExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE，运行时替换）
     * Request URL template ({0} is the contract code, replaced at runtime).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();

        /** 订单类型（10 限价单 / 11 市价单）/ Order type (10=limit, 11=market) */
        bodyParam.put("type", "11");

        /** 方向（open_long 开多 / open_short 开空 / close_long 平多 / close_short 平空）
         *  Side (open_long, open_short, close_long, close_short) */
        bodyParam.put("side", "open_long");

        /** 委托价格 / Order price */
        bodyParam.put("price", "11");

        /** 委托数量 / Order quantity */
        bodyParam.put("amount", "1");

        /** 被动委托标志（0 不被动 / 1 被动委托，即 Post-Only）
         *  Post-only flag (0=off, 1=post-only/maker only) */
        bodyParam.put("beMaker", "0");

        /** 路径参数：合约 CODE（例如 BTCUSDT）/ Path param: contract code (e.g. BTCUSDT) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap<>(), bodyParam);
        System.out.println(result);
    }
}
