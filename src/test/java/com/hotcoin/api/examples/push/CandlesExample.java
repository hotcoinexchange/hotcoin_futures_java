package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket K 线（蜡烛图）推送订阅示例 / WebSocket candlestick (K-line) push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅指定合约的实时 K 线数据推送。
 * Demonstrates how to subscribe to real-time candlestick (K-line) push data
 * for a given contract via WebSocket.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code candles}</p>
 *
 * @author hugh
 * @date 2024/4/10
 */
@Slf4j
public class CandlesExample {

    /**
     * 构造 K 线订阅消息 JSON 字符串
     * Builds the JSON subscription message for the candlestick channel.
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
        /** 订阅频道：K 线 / Subscription channel: candlestick */
        params.put("type", "candles");
        /** 合约 CODE（小写，例如 btcusdt）/ Contract code (lowercase, e.g. btcusdt) */
        params.put("contractCode", "btcusdt");
        /** K 线时间粒度（如 1min / 5min / 15min / 1day 等）
         *  Candlestick interval (e.g. 1min, 5min, 15min, 1day) */
        params.put("granularity", "1min");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅 K 线数据（公开频道，无需登录）
         *  Establish WebSocket and subscribe to candlestick data (public channel, no auth required) */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), null, null, true);
    }
}
