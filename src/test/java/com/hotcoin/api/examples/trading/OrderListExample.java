package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单列表
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class OrderListExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        String uri = "/api/v1/perpetual/products/BTCUSDT/list";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
