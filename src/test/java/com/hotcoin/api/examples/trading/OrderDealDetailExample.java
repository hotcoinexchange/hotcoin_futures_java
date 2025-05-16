package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单交易详情D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class OrderDealDetailExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/orderDetail";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 订单ID */
        pathParam.put("orderId", "3253898960830720");

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
