package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 条件单下单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class ConditionalOrderExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 类型（12 条件单） */
        pathParam.put("type", "12");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        pathParam.put("side", "open_long");
        /** 价格（触发后下单价格（限价单price必传）） */
        pathParam.put("price", "11");
        /** 数量 */
        pathParam.put("amount", "1");
        /** 触发类型（默认mark，标记价格：mark，最新价格：last） */
        pathParam.put("triggerBy", "mark");
        /** 触发价 */
        pathParam.put("triggerPrice", "10");
        /** 当前标记价格（计划委托时不传；止盈止损时传当前合约价格, 用于判断止盈止损方向） */
        pathParam.put("currentPrice", "10");
        /** 委托类型（10-限价，11-市价） */
        pathParam.put("algoType", "10");
        /** 被动委托：0:不被动委托 1:被动委托 */
        pathParam.put("beMaker", "0");

        String uri = "/api/v1/perpetual/products/BTCUSDT/order";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
