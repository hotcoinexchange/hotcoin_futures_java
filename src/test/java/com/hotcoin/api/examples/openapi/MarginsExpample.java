package com.hotcoin.api.examples.openapi;

import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 保证金账户查询
 *
 * @author zenghaihui
 * @date 2024/5/11
 */
public class MarginsExpample {

    /**
     * 请求url模版
     */
    static String host = "https://test-perpetual.hotcx.com";
    static String uriTemplate = "/api/v1/perpetual/admin/insurance-account/bills";
    static String accessKey = "8c80118b4d2a4a4f8a05a5451679bb3e";
    static String secretKey = "AFD965CC508592553E32B72C0BDC7E84";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 开始时间 */
        pathParam.put("startDate", "1712734214561");
        /** 结束时间 */
        pathParam.put("endDate", "1712734214561");
        /** 合约code	 */
        pathParam.put("contractCode", "BTCUSDT");
        /** 页数 */
        pathParam.put("page", "1");
        /** 每页数量 */
        pathParam.put("pageSize", "20");
        String uri = uriTemplate;
        String result = HttpUtil.hostGet(host, uri, pathParam, accessKey, secretKey);
        System.out.println(result);
    }
}

//100400312
