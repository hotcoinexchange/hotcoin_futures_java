package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置杠杆D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionLeverExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/lever";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();
        /** 杠杆类型(全仓:0,逐仓:1) */
        bodyParam.put("type", "1");
        /** 杠杆(1~125) */
        bodyParam.put("lever", "2");
        /** 方向(多仓:long，空仓:short) */
        bodyParam.put("side", "short");
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap<>(), bodyParam);
        System.out.println(result);
    }
}
