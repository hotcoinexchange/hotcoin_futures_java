package com.hotcoin.api.examples.push;

import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * 登陆
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class SigninExample {


    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(HotcoinApiUrls.TEST_URL, null, PrivateApiConfig.HUGH_KEY, true);
    }
}
