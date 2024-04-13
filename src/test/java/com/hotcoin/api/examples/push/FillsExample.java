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
 * 最新成交
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
public class FillsExample {
    /** 访问地址 */
    static String url ="wss://test-perpetual-wss.hotcx.com";
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
        params.put("type","fills");
        /** 合约CODE */
        params.put("contractCode","BTCUSDT");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),false,true);
    }


//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","type":"fills","contractCode":"BTCUSDT"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"channel":"subscribe","type":"fills","env":0,"contractCode":"BTCUSDT"}
//    Received message: {"biz":"perpetual","data":[],"type":"fills","env":0,"contractCode":"BTCUSDT","timestamp":1712802153103}
//    Received message: {"biz":"perpetual","data":[["70495.35","7","short",1712814248494,0]],"type":"fills","env":0,"contractCode":"btcusdt","timestamp":1712814248502}
//    Received message: {"biz":"perpetual","data":[["70495.35","33","short",1712814248594,0],["70495.35","15","short",1712814248594,0]],"type":"fills","env":0,"contractCode":"btcusdt","timestamp":1712814248602}
}
