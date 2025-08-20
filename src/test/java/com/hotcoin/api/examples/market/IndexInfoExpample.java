package com.hotcoin.api.examples.market;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 指数价成分信息
 * @author Hugh
 * @date 2025/8/20 18:35
 * @description
 */
public class IndexInfoExpample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/indexInfo";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }

}
