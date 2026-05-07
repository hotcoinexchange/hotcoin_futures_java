package com.hotcoin.api.examples.push;

import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * WebSocket 登录鉴权示例 / WebSocket sign-in (authentication) example.
 *
 * <p>演示如何建立 WebSocket 连接并完成登录鉴权，以便订阅需要身份验证的私有频道
 * （如用户资产、订单、仓位等）。
 * Demonstrates how to establish a WebSocket connection and perform authentication,
 * which is required for subscribing to private channels
 * (e.g. user assets, orders, positions).</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 *
 * @author hugh
 * @date 2024/4/12
 */
public class SigninExample {

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并发送登录鉴权消息（短连接模式，发完即断）
         *  Establish WebSocket connection and send sign-in message (short-connect: closes after auth) */
        WebSocketUtil.webConnect(
                HotcoinApiUrls.PRO_URL,           // WebSocket 服务地址 / WebSocket server URL
                null,                              // 无订阅消息 / No subscription message
                PrivateApiConfig.YOUR_KEY,         // AccessKey 用于登录 / AccessKey for authentication
                PrivateApiConfig.YOUR_SECRET_KEY,  // SecretKey 用于签名 / SecretKey for HMAC signing
                true                               // shortConnect=true：登录后立即关闭 / Close immediately after login
        );
    }
}
