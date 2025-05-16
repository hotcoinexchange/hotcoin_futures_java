package com.hotcoin.api.swapExample;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @description: 设置自动追加保证金
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class PositionSettingExample {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("value", "0");
        String contractCode = "btcusdt";
        String uri = MessageFormat.format("/api/v1/perpetual/position/{0}/setting", contractCode);
        String post = HttpUtil.post(GlobalConfigEnum.TEST, uri, params, params);
        System.out.println(post);
    }
}
