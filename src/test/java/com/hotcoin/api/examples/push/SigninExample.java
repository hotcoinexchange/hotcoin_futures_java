package com.hotcoin.api.examples.push;

import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * 登陆
 *
 * @author hugh
 * @date 2024/4/12
 */
public class SigninExample {


    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, null, PrivateApiConfig.YOUR_KEY, true);
    }
}
