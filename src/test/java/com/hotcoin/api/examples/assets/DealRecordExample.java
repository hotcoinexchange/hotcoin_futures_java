package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 成交记录D
 *
 * @author hugh
 * @date 2024/4/16
 */
public class DealRecordExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/bills/deal-record";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 开始时间 */
        pathParam.put("startDate", "1712734214561");
        /** 结束时间 */
        pathParam.put("endDate", "1713339014561");
        /** 合约code	 */
        pathParam.put("contractCode", "btcusdt");
        /** 页数 */
        pathParam.put("page", "1");
        /** 每页数量 */
        pathParam.put("pageSize", "20");
        /** 开始id */
        pathParam.put("startId", "1");
        /** 结束id */
        pathParam.put("endId", "10000000");
        String uri = uriTemplate;
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
