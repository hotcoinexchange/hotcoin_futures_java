package com.hotcoin.api.examples.push;

import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * 登陆
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class SigninExample {
    /**
     * 访问地址
     */
    static String url = "wss://wss-ct.hotcoin.fit";


    public static void main(String[] args) {
        /** 秘钥 */
        PrivateApiConfig.ACCESS_KEY = "77827653e2b347fab36bfa69f2893dfb";
        PrivateApiConfig.SECRET_KEY = "058B4DECEFEF68EA59BBF132617627B2";
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url, null, PrivateApiConfig.ACCESS_KEY, true);
    }
}
