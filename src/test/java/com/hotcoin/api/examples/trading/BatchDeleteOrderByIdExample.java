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
        /** 订单号 */
        pathParam.put("id", "[80581909355536,80581909355537]");

        String uri = "/api/v1/perpetual/products/BTCUSDT/batch-delete-order";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
