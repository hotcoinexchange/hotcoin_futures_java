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
        /** k线 */
        params.put("type","candles");
        /** 合约CODE */
        params.put("contractCode","BTCUSDT");
        /** 频率 */
        params.put("granularity","1min");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.longConnect(url,paramsGenerate());
    }
//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","granularity":"1min","type":"candles","contractCode":"BTCUSDT"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"granularity":"1min","channel":"subscribe","type":"candles","env":0,"contractCode":"BTCUSDT"}
//    Received message: {"biz":"perpetual","data":[[1712803080000,"70911.47","70942.33","70924.19","70911.47","16364","1160703.92868"],[1712803140000,"70864.63","70921.61","70911.47","70872.27","10492","743920.9916"],[1712803200000,"70872.27","70918.11","70872.27","70899.21","6038","428038.61074"]],"granularity":"1min","type":"candles","env":0,"contractCode":"BTCUSDT","timestamp":1712803235967}
//    Received message: {"biz":"perpetual","data":[[1712803200000,"70872.27","70918.11","70872.27","70895.61","6058","429456.52294"]],"granularity":"1min","type":"candles","env":0,"contractCode":"btcusdt","timestamp":1712803237414}
//    Received message: {"biz":"perpetual","data":[[1712803200000,"70872.27","70918.11","70872.27","70895.13","6064","429881.89372"]],"granularity":"1min","type":"candles","env":0,"contractCode":"btcusdt","timestamp":1712803237697}
//    Received message: {"biz":"perpetual","data":[[1712803200000,"70872.27","70918.11","70872.27","70895.13","6066","430023.68398"]],"granularity":"1min","type":"candles","env":0,"contractCode":"btcusdt","timestamp":1712803237795}
}
