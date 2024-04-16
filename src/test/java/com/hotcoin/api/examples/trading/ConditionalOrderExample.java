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
        pathParam.put("type","12");
        pathParam.put("triggerBy","mark");
        pathParam.put("triggerPrice","10");
        pathParam.put("side","open_long");
        pathParam.put("price","11");
        pathParam.put("amount","1");
        pathParam.put("currentPrice","10");
        pathParam.put("algoType","10");
        pathParam.put("beMaker",String.valueOf(System.currentTimeMillis()));

        String uri = "/api/v1/perpetual/products/BTCUSDT/order";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
