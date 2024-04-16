package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据订单号批量撤单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class BatchDeleteOrderByIdExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("type","11");
        pathParam.put("side","open_long");
        pathParam.put("price","11");
        pathParam.put("amount","1");
        pathParam.put("beMaker",String.valueOf(System.currentTimeMillis()));

        String uri = "/api/v1/perpetual/products/BTCUSDT/order";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
