package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有合约指数价格,标记价格和资金费率
 *
 * @author hugh
 * @date 2024/4/12
 */
public class FundRatesExample {

    /**
     * 请求参数制造方法
     */
    static String paramsGenerate() {
        Map<String, Object> pushMsg = new HashMap<>();
        /** 请求类型 */
        pushMsg.put("event", "subscribe");
        Map<String, Object> params = new HashMap<>();
        /** 业务类型 */
        params.put("biz", "perpetual");
        /** 所有资金费率 */
        params.put("type", "fund_rates");
        /** 是否序列化 */
        params.put("serialize", false);
        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), PrivateApiConfig.YOUR_KEY, true);
    }

}
