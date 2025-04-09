package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 多个行情
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
public class TickersExample {
    /**
     * 访问地址
     */
    static String url ="wss://test-perpetual-wss.hotcx.com";

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
        /** 订阅项 */
        params.put("env", "0");
        /** 订阅项 */
        params.put("type", "tickers");
        /** 是否序列化 */
        params.put("serialize", false);
        /** 时区 */
        params.put("granularity", "UTC+05:00");
        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url, paramsGenerate(), null, true);
    }
}
