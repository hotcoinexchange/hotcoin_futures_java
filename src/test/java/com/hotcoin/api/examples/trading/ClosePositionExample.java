package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 市价全平
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class ClosePositionExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        String uri = "/api/v1/perpetual/products/BTCUSDT/long/closePosition";
        String result = HttpUtil.post(GlobalConfigEnum.HUGH, uri, pathParam, null);
        System.out.println(result);
    }
}
