package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 撤单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class DeleteOrderExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        String uri = "/api/v1/perpetual/products/BTCUSDT/order/1";
        String result = HttpUtil.del(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
