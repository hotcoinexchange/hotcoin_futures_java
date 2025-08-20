package com.hotcoin.api.examples.market;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * K线
 * @author Hugh
 * @date 2025/8/20 18:35
 * @description
 */
public class PublicCandlesExpample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/candles";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** k线类型 */
        pathParam.put("kline", "1min");
        /** k线数量 */
        pathParam.put("size", "50");
        /** K线类型 */
        pathParam.put("klineType", "1");
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }

}
