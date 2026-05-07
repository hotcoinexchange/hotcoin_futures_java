package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 新币信息推送订阅示例 / WebSocket new currency info push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅平台新上线币对的实时信息推送。
 * Demonstrates how to subscribe to real-time notifications about newly listed
 * trading pairs on the platform via WebSocket.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code new_currency}</p>
 *
 * <p>参考订阅消息 / Reference subscription message:
 * <pre>{"event":"subscribe","params":{"base":"","biz":"perpetual","env":0,"quote":"","serialize":true,"type":"new_currency","zip":false}}</pre>
 * </p>
 *
 * @author hugh
 * @date 2024/4/10
 */
@Slf4j
public class NewCurrencyExample {

    /**
     * 构造新币信息订阅消息 JSON 字符串
     * Builds the JSON subscription message for the new currency info channel.
     *
     * @return 序列化后的订阅消息字符串 / Serialized subscription message string
     */
    // 参考消息结构 / Reference message structure:
    // {"event":"subscribe","params":{"base":"","biz":"perpetual","env":0,"quote":"","serialize":true,"type":"new_currency","zip":false}}
    static String paramsGenerate() {
        Map<String, Object> pushMsg = new HashMap<>();
        /** 请求类型：订阅 / Request type: subscribe */
        pushMsg.put("event", "subscribe");

        Map<String, Object> params = new HashMap<>();
        /** 业务线类型：永续合约 / Business type: perpetual futures */
        params.put("biz", "perpetual");
        /** 环境标识（0=正式环境）/ Environment flag (0=production) */
        params.put("env", "0");
        /** 订阅频道：新币信息 / Subscription channel: new currency info */
        params.put("type", "new_currency");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅新币信息（公开频道，无需登录）
         *  Establish WebSocket and subscribe to new currency info (public channel, no auth required) */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), null, null, true);
    }
}
