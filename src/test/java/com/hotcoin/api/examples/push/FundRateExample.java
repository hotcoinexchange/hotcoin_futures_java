package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 单合约资金费率推送订阅示例 / WebSocket single-contract fund rate push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅指定合约的实时指数价格、标记价格和资金费率推送。
 * Demonstrates how to subscribe to real-time index price, mark price and funding rate
 * push data for a single contract via WebSocket.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code fund_rate}</p>
 *
 * <p>注意：{@code main} 方法还包含一个压测循环，用于验证大量连接的并发性能。
 * Note: The {@code main} method also contains a stress-test loop for concurrent connection validation.</p>
 *
 * @author hugh
 * @date 2024/4/12
 */
@Slf4j
public class FundRateExample {

    /**
     * 构造单合约资金费率订阅消息 JSON 字符串
     * Builds the JSON subscription message for the single-contract fund rate channel.
     *
     * @return 序列化后的订阅消息字符串 / Serialized subscription message string
     */
    static String paramsGenerate() {
        Map<String, Object> pushMsg = new HashMap<>();
        /** 请求类型：订阅 / Request type: subscribe */
        pushMsg.put("event", "subscribe");

        Map<String, Object> params = new HashMap<>();
        /** 业务线类型：永续合约 / Business type: perpetual futures */
        params.put("biz", "perpetual");
        /** 订阅频道：资金费率（单合约）/ Subscription channel: fund rate (single contract) */
        params.put("type", "fund_rate");
        /** 合约 CODE（例如 BTCUSDT）/ Contract code (e.g. BTCUSDT) */
        params.put("contractCode", "BTCUSDT");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    /**
     * 压测入口：对多个合约的 ticker / candles / fund_rate / depth 频道发起大量短连接
     * Stress-test entry: fires many short-lived connections to multiple channels across several contracts.
     */
    public static void main(String[] args) throws InterruptedException {
        // 循环 100000 次，模拟高并发场景 / Loop 100000 times to simulate high concurrency
        for (int i = 0; i < 100000; i++) {
            // 待测试的合约 CODE 列表 / List of contract codes to test
            String[] custcodeList = {"BTCUSDT", "BCHUSDT", "SOLUSDT", "LTCUSDT", "ETCUSDT",
                    "ZRXUSDT", "ETHUSDT", "DOGEUSDT", "DASHUSDT", "FILUSDT"};
            for (String custcode : custcodeList) {
                // 订阅 ticker（单币对行情）/ Subscribe to ticker (single-symbol)
                String ticker = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"ticker\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, ticker, false);

                // 订阅 candles（K 线）/ Subscribe to candles
                String candles = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"granularity\":\"1min\",\"type\":\"candles\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, candles, false);

                // 订阅 fund_rate（资金费率）/ Subscribe to fund_rate
                String fundRate = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"fund_rate\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, fundRate, false);

                // 订阅 depth（订单簿深度）/ Subscribe to depth (order book)
                String depth = "{\"event\":\"subscribe\",\"params\":{\"serialize\":false,\"biz\":\"perpetual\",\"type\":\"depth\",\"contractCode\":\"" + custcode + "\"}}";
                WebSocketUtil.webPressureConnect(HotcoinApiUrls.PRO_URL, depth, false);
            }
            Thread.sleep(50); // 每批次间隔 50ms / Wait 50ms between batches
        }
    }
}
