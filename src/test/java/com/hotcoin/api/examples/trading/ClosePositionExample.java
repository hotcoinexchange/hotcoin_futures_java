package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 市价全平D
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class ClosePositionExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/{1}/closePosition";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 路径参数：合约code,方向(long 多仓 short 空仓) */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "long");
        String result = HttpUtil.post(GlobalConfigEnum.HUGH, uri, pathParam, new HashMap<>());
        System.out.println(result);
    }
}
