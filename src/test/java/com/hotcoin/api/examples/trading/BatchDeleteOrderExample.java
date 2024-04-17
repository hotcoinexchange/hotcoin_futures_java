package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 批量撤单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class BatchDeleteOrderExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        String uri = "/api/v1/perpetual/products/BTCUSDT/order";
        String result = HttpUtil.del(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
