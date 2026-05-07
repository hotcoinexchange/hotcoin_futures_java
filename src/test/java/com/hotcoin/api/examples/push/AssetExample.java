package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 用户资产推送订阅示例（需要登录鉴权）
 * WebSocket user asset push subscription example (authentication required).
 *
 * <p>演示如何在完成 WebSocket 登录后，订阅当前账户的实时资产变动推送。
 * Demonstrates how to subscribe to real-time account asset updates after
 * completing WebSocket authentication.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code assets}</p>
 * <p>鉴权要求 / Auth required: 是 / Yes</p>
 *
 * @author hugh
 * @date 2024/4/12
 */
public class AssetExample {

    /**
     * 构造用户资产订阅消息 JSON 字符串
     * Builds the JSON subscription message for the user asset channel.
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
        /** 订阅频道：用户资产 / Subscription channel: user assets */
        params.put("type", "assets");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅用户资产（需要登录鉴权）
         *  Establish WebSocket and subscribe to user assets (authentication required) */
        WebSocketUtil.webConnect(
                HotcoinApiUrls.PRO_URL,
                paramsGenerate(),
                PrivateApiConfig.YOUR_KEY,        // AccessKey 用于登录 / AccessKey for login
                PrivateApiConfig.YOUR_SECRET_KEY,  // SecretKey 用于签名 / SecretKey for signing
                true
        );
    }
}
