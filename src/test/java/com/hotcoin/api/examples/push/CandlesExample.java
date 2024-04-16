package com.hotcoin.api.examples.push;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * newex_push项目
 * k线:candles
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
public class CandlesExample {
    /** 访问地址 */
    static String url ="wss://wss-ct.hotcoin.fit";

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
        /** k线 */
        params.put("type","candles");
        /** 合约CODE */
        params.put("contractCode","BTCUSDT");
        /** K线类型 */
        params.put("granularity","1min");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),false,true);
    }
}
