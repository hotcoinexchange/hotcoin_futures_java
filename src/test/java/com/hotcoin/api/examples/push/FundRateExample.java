package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 合约指数价格,标记价格和资金费率
 *
 * @author hugh
 * @date 2024/4/12
 */
@Slf4j
public class FundRateExample {
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
        /** 资金费率 */
        params.put("type", "fund_rate");
        /** 合约CODE */
        params.put("contractCode", "BTCUSDT");
        /** 是否序列化 */
        params.put("serialize", false);
        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<100000 ;i++) {
            String custcodeList[] = {"BTCUSDT", "BCHUSDT", "SOLUSDT", "LTCUSDT", "ETCUSDT", "ZRXUSDT", "ETHUSDT", "DOGEUSDT", "DASHUSDT", "FILUSDT"};
            for (String custcode : custcodeList) {
                String ticker = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"ticker\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, ticker, false);
                String candles = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"granularity\":\"1min\",\"type\":\"candles\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, candles, false);
                String fundRate = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"fund_rate\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, fundRate, false);
                String depth = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"depth\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, depth, false);
            }
            Thread.sleep(50);
        }
    }
}
