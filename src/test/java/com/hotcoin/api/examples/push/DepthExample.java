package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 深度
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
@Slf4j
public class DepthExample {
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
        params.put("type", "depth");
        /** 合约CODE */
        params.put("contractCode", "BTCUSDT");
        /** 是否序列化 */
        params.put("serialize", false);
        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {

        log.error(paramsGenerate());
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url, paramsGenerate(), false, true);
    }

}
