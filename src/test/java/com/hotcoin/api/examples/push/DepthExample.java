package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.utils.WebSocketUtil;
import com.hotcoin.swap_api.domain.Result;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zenghaihui
 * @date 2024/4/10
 */
public class DepthExample {
    /** 访问地址 */
    static String url ="ws://test-perpetual-wss.hotcx.com";
//    static String url ="ws://127.0.0.1:8104";

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
        /** 订阅项 */
        params.put("type","depth");
        /** 合约CODE */
        params.put("contractCode","LTCUSDT");
        /** 频率 */
        params.put("granularity","1");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.longConnect(url,paramsGenerate());
    }

//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","granularity":"1","type":"depth","contractCode":"LTCUSDT"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"granularity":"1","channel":"subscribe","type":"depth","env":0,"contractCode":"LTCUSDT"}
//    Received message: {"biz":"perpetual","data":{"asks":[["250.00000","94","94"]],"bids":[["216.01000","5319","5319"]]},"granularity":"1","type":"depth","env":0,"contractCode":"LTCUSDT","timestamp":1712798513490}

}
