package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 全市场行情（All Tickers）推送订阅示例 / WebSocket all-symbols tickers push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅所有合约的实时行情汇总推送。
 * Demonstrates how to subscribe to real-time ticker data for all contracts
 * via a single WebSocket subscription.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code tickers}</p>
 *
 * @author hugh
 * @date 2024/4/10
 */
public class TickersExample {

    /**
     * 构造全市场行情订阅消息 JSON 字符串
     * Builds the JSON subscription message for the all-symbols tickers channel.
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
        /** 环境标识（0=正式环境）/ Environment flag (0=production) */
        params.put("env", "0");
        /** 订阅频道：全市场行情 / Subscription channel: all-symbols tickers */
        params.put("type", "tickers");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);
        /** 时区设置（影响 K 线开盘时间等，例如 UTC+05:00）
         *  Timezone (affects candle open time, etc., e.g. UTC+05:00) */
        params.put("granularity", "UTC+05:00");

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅全市场行情（公开频道，无需登录）
         *  Establish WebSocket and subscribe to all tickers (public channel, no auth required) */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), null, null, true);
    }
}
