package com.hotcoin.api.examples.publics;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器时间
 * @author Hugh
 * @date 2025/8/20 18:35
 * @description
 */
public class TimeExpample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/public/time";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 路径参数：合约code */
        String uri = uriTemplate;
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }

}
