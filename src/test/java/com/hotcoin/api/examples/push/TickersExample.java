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
 * 多个行情
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
public class TickersExample {
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
        /** 订阅项 */
        params.put("type","tickers");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),false,true);
    }
//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","type":"tickers"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"channel":"subscribe","type":"tickers","env":0}
//    Received message: {"biz":"perpetual","data":[[1712762705687,"99999.00","60000.00","15179004","1048002762","69191.55","68444.85","-746","-0.3","68444.39","68445.31","btcusdt","491432.98",0]],"type":"tickers","env":0,"timestamp":1712762707373}
//    Received message: {"biz":"perpetual","data":[[1712762705687,"99999.00","60000.00","15179004","1048002762","69191.55","68437.99","-746","-0.31","68444.39","68445.31","btcusdt","491383.72",0]],"type":"tickers","env":0,"timestamp":1712762710377}
//    Received message: {"biz":"perpetual","data":[[1712762710742,"99999.00","60000.00","15179438","1048032467","69191.55","68437.68","-753","-0.31","39500","0","btcusdt","491381.50",0]],"type":"tickers","env":0,"timestamp":1712762713404}

}
