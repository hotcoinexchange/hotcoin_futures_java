package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取所有档位对应的保证金率和杠杆
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class LeverGearsExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/lever-gears";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
