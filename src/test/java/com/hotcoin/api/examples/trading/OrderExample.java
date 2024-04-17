package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 普通单下单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class OrderExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{}/order";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 类型（10 限价 11 市价） */
        pathParam.put("type", "11");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        pathParam.put("side", "open_long");
        /** 价格 */
        pathParam.put("price", "11");
        /** 数量 */
        pathParam.put("amount", "1");
        /** 被动委托：0:不被动委托 1:被动委托 */
        pathParam.put("beMaker", "0");

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
