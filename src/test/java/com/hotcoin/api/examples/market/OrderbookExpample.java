package com.hotcoin.api.examples.market;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 深度信息
 * @author Hugh
 * @date 2025/8/20 18:35
 * @description
 */
public class OrderbookExpample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/products/{0}/orderbook";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }

}
