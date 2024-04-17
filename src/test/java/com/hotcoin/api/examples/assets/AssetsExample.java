package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 我的资产D
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class AssetsExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/account/assets/{0}";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();


        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
