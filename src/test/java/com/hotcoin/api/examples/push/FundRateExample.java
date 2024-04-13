package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 合约指数价格,标记价格和资金费率
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class FundRateExample {
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
        /** 资金费率 */
        params.put("type","fund_rate");
        /** 合约CODE */
        params.put("contractCode","btcusdt");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),true,true);
    }

//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","type":"fund_rate","contractCode":"btcusdt"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"channel":"subscribe","type":"fund_rate","env":0,"contractCode":"btcusdt"}
//    Received message: {"biz":"perpetual","data":["btcusdt","67594.61","67592.90","0.0001","0.0001",7294622,"24054","436785.31","436796.42",0,"usdt","btc","usdt","USDT","BTC","USDT","0"],"type":"fund_rate","env":0,"contractCode":"btcusdt","timestamp":1713016705540}
//    Received message: {"biz":"perpetual","data":["btcusdt","67591.71","67590.00","0.0001","0.0001",7291604,"24054","436766.58","436777.68",0,"usdt","btc","usdt","USDT","BTC","USDT","0"],"type":"fund_rate","env":0,"contractCode":"btcusdt","timestamp":1713016708398}
//    Received message: {"biz":"perpetual","data":["btcusdt","67563.71","67562.00","0.0001","0.0001",7288555,"24054","436585.64","436596.74",0,"usdt","btc","usdt","USDT","BTC","USDT","0"],"type":"fund_rate","env":0,"contractCode":"btcusdt","timestamp":1713016711480}

}
