package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 订单簿（深度）推送订阅示例 / WebSocket order book (market depth) push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅指定合约的实时订单簿（买卖盘深度）推送数据。
 * Demonstrates how to subscribe to real-time order book (bid/ask depth) push data
 * for a given contract via WebSocket.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code depth}</p>
 *
 * @author hugh
 * @date 2024/4/10
 */
@Slf4j
public class DepthExample {

    /**
     * 构造订单簿订阅消息 JSON 字符串
     * Builds the JSON subscription message for the order book channel.
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
        /** 订阅频道：订单簿深度 / Subscription channel: order book depth */
        params.put("type", "depth");
        /** 合约 CODE（例如 BTCUSDT）/ Contract code (e.g. BTCUSDT) */
        params.put("contractCode", "BTCUSDT");
        /** 是否序列化压缩（false=不压缩，直接返回 JSON）
         *  Serialize/compress flag (false=no compression, returns raw JSON) */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅深度数据（公开频道，无需登录）
         *  Establish WebSocket and subscribe to depth data (public channel, no auth required) */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), null, null, true);
    }
}
