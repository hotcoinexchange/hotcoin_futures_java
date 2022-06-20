package com.hotcoin.api.swapExample;


import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @description: 修改保证金
 * @author: hotcoin
 * @date: 2022/4/15
 **/
public class ChangeMarginExample {
    /**
     * contract_code = 'bchusdt'
     * side = 'long'
     * params = {
     * 'side': side,
     * 'margin': 1000
     * }
     * uri = f'/api/v1/perpetual/position/{contract_code}/change-margin'
     * response = base_util.post(ACCESS_KEY, SECRET_KEY, HOST, uri, params, params)
     * print(response)
     */
    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("side", "long");
        pathParam.put("margin", "10");
        String uri = "/api/v1/perpetual/position/bchusdt/change-margin";
        String result = HttpUtil.post(GlobalConfigEnum.TEST, uri, pathParam, pathParam);
        System.out.println(result);
    }
}
