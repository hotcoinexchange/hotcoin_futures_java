package com.hotcoin.api.examples.assets;

import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的资产
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class AssetsExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        String uri = "/api/v1/perpetual/account/assets/btcusdt";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
