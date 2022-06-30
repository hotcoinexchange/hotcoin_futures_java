package com.hotcoin.api.example;

import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 市价全平
 *
 * @version V1.0
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class ClosePositionExample {

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        String contractCode = "bchusdt";
        String side = "long";
        String uri = MessageFormat.format("/api/v1/perpetual/products/{0}/{1}/closePosition", contractCode, side);
        String post = HttpUtil.post(GlobalConfigEnum.TEST, uri, params, params);
        System.out.println(post);
    }

}
