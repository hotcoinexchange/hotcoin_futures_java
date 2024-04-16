package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 资产列表
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class AssetsListExample {


    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        String uri = "/api/v1/perpetual/account/assets";
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
