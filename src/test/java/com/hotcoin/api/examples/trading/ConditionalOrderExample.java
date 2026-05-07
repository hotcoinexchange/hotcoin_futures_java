package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 条件单下单示例（计划委托 / 止盈止损）/ Place a conditional order example (plan order / take-profit / stop-loss).
 *
 * <p>演示如何提交一笔条件单（触发价达到时才执行的委托）。
 * Demonstrates how to submit a conditional order that executes when a trigger price is reached.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/products/{contractCode}/order</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class ConditionalOrderExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 订单类型（12 = 条件单）/ Order type (12 = conditional order) */
        pathParam.put("type", "12");

        /** 触发价格类型（mark=标记价格，last=最新成交价，默认 mark）
         *  Trigger price type (mark=mark price, last=last trade price, default: mark) */
        pathParam.put("triggerBy", "mark");

        /** 触发价格（到达此价格时触发委托）/ Trigger price (order is triggered when price hits this value) */
        pathParam.put("triggerPrice", "10");

        /** 方向（open_long 开多 / open_short 开空 / close_long 平多 / close_short 平空）
         *  Side (open_long, open_short, close_long, close_short) */
        pathParam.put("side", "open_long");

        /** 委托价格（触发后下单的价格；限价单必传）/ Order price after trigger (required for limit orders) */
        pathParam.put("price", "11");

        /** 当前标记价格（计划委托时不传；止盈止损时传入当前合约价，用于判断方向）
         *  Current mark price (omit for plan orders; pass current contract price for TP/SL to determine direction) */
        pathParam.put("currentPrice", "20");

        /** 触发后委托类型（10 限价 / 11 市价）/ Order type after trigger (10=limit, 11=market) */
        pathParam.put("algoType", "10");

        /** 委托数量 / Order quantity */
        pathParam.put("amount", "1");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, pathParam, pathParam);
        System.out.println(result);
    }
}
