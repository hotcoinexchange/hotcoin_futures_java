package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 仓位列表
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class PositionListExample {


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
