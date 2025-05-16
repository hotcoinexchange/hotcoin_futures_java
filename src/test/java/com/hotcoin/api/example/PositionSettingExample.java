package com.hotcoin.api.example;

import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置自动追加保证金
 *
 * @version V1.0
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
