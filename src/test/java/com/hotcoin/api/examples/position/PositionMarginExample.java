package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改保证金D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionMarginExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/change-margin";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();
        /** 保证金数量（正数表示添加保证金，负数表示减少保证金） */
        bodyParam.put("margin", "1000");
        /** 方向(long 多仓 short 空仓) */
        bodyParam.put("side", "long");
        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap<>(), bodyParam);
        System.out.println(result);
    }
}
