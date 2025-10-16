package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 条件单、止盈止损批量撤单
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchDeleteOrderConditionExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/orders/condition";

    public static void main(String[] args) {
        List<Long> ids = new ArrayList<>();
        ids.add(3254001157243472L);
        /** 路径参数：合约code,订单id */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT", "3253898960830720");
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, new HashMap<>(), ids);
        System.out.println(result);
    }
}
