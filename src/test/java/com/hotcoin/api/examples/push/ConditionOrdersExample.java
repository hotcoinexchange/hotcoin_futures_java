package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 条件订单
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class ConditionOrdersExample {
    /** 访问地址 */
    static String url ="wss://test-perpetual-wss.hotcx.com";

    /**
     * 请求参数制造方法
     * @return
     */
    static String paramsGenerate(){
        Map<String,Object> pushMsg = new HashMap<>();
        /** 请求类型 */
        pushMsg.put("event","subscribe");
        Map<String,Object> params = new HashMap<>();
        /** 业务类型 */
        params.put("biz","perpetual");
        /** 条件订单 */
        params.put("type","condition_orders");
        /** 模拟交易 */
        params.put("env",1);
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),true,true);
    }
}
