package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 资产列表D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class AssetsListExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/account/assets";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约code */
        String uri = uriTemplate;
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
