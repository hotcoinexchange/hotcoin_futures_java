package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 全合约资金费率推送订阅示例 / WebSocket all-contracts fund rates push subscription example.
 *
 * <p>演示如何通过 WebSocket 订阅所有合约的实时资金费率汇总推送。
 * Demonstrates how to subscribe to real-time fund rate data for all contracts
 * via a single WebSocket subscription.</p>
 *
 * <p>连接地址 / WebSocket URL: {@link HotcoinApiUrls#PRO_URL}</p>
 * <p>订阅类型 / Subscription type: {@code fund_rates}</p>
 *
 * @author hugh
 * @date 2024/4/12
 */
public class FundRatesExample {

    /**
     * 构造全合约资金费率订阅消息 JSON 字符串
     * Builds the JSON subscription message for the all-contracts fund rates channel.
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
        /** 订阅频道：所有合约资金费率 / Subscription channel: all-contracts fund rates */
        params.put("type", "fund_rates");
        /** 是否序列化压缩 / Serialize/compress flag */
        params.put("serialize", false);

        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 建立 WebSocket 连接并订阅全合约资金费率（需要登录鉴权）
         *  Establish WebSocket and subscribe to all-contracts fund rates (authentication required) */
        WebSocketUtil.webConnect(
                HotcoinApiUrls.PRO_URL,
                paramsGenerate(),
                PrivateApiConfig.YOUR_KEY,        // AccessKey 登录鉴权 / AccessKey for auth
                PrivateApiConfig.YOUR_SECRET_KEY,  // SecretKey 签名 / SecretKey for signing
                true
        );
    }
}
