package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单详情
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class OrderDetailsExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/{1}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约code,订单id */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "1");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
