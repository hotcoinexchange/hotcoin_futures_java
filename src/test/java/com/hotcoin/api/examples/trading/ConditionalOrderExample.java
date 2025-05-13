package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 条件单下单D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class ConditionalOrderExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 类型（12 条件单） */
        pathParam.put("type", "12");
        /** 触发类型（默认mark，标记价格：mark，最新价格：last） */
        pathParam.put("triggerBy", "mark");
        /** 触发价 */
        pathParam.put("triggerPrice", "10");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        pathParam.put("side", "open_long");
        /** 价格（触发后下单价格（限价单price必传）） */
        pathParam.put("price", "11");
        /** 当前标记价格（计划委托时不传；止盈止损时传当前合约价格, 用于判断止盈止损方向） */
        pathParam.put("currentPrice", "20");
        /** 委托类型（10-限价，11-市价） */
        pathParam.put("algoType", "10");
        /** 数量 */
        pathParam.put("amount", "1");

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, pathParam, pathParam);
        System.out.println(result);
    }
}
