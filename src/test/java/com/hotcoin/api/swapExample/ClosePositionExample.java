package com.hotcoin.api.swapExample;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @description: 市价全平
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
