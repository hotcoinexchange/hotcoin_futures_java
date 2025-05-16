package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置自动追加保证金D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionSettingExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/setting";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();
        bodyParam.put("value", "1");
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, bodyParam, bodyParam);
        System.out.println(result);
    }
}
