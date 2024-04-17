package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 普通单下单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class OrderExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 类型（10 限价 11 市价） */
        pathParam.put("type", "11");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        pathParam.put("side", "open_long");
        /** 价格 */
        pathParam.put("price", "11");
        /** 数量 */
        pathParam.put("amount", "1");
        /** 被动委托：0:不被动委托 1:被动委托 */
        pathParam.put("beMaker", "0");

        String uri = "/api/v1/perpetual/products/BTCUSDT/order";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
