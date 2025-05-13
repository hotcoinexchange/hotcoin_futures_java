package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 根据订单号批量撤单D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchDeleteOrderByIdExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/batch-delete-order";

    public static void main(String[] args) {
        List<Long> ids = new ArrayList<>();
        ids.add(3254001157243472L);

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.del(GlobalConfigEnum.YOUR, uri, new HashMap<>(), ids);
        System.out.println(result);
    }
}
