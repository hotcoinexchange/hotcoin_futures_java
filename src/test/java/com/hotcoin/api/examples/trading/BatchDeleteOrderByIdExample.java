package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据订单号批量撤单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class BatchDeleteOrderByIdExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{}/batch-delete-order";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 订单号 */
        pathParam.put("id", "[80581909355536,80581909355537]");

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
