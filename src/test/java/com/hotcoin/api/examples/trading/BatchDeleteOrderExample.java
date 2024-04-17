package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * 批量撤单-not d
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class BatchDeleteOrderExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/order";

    public static void main(String[] args) {

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.del(GlobalConfigEnum.HUGH, uri, new HashMap<>(), null);
        System.out.println(result);
    }
}
