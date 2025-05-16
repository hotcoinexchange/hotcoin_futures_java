package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 撤单D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class DeleteOrderExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order/{1}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约code,订单id */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, pathParam, new HashMap<>());
        System.out.println(result);
    }
}
