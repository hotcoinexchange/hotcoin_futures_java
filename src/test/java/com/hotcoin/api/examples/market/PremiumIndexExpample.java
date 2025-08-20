package com.hotcoin.api.examples.market;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 最新标记价格和资金费率
 * @author Hugh
 * @date 2025/8/20 18:35
 * @description
 */
public class PremiumIndexExpample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/premiumIndex";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }

}
