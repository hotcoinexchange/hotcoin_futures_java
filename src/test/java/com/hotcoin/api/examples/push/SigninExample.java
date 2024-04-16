package com.hotcoin.api.examples.push;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.SignatureGenerator;
import com.hotcoin.api.utils.UrlBuilder;
import com.hotcoin.api.utils.WebSocketUtil;

/**
 * 登陆
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class SigninExample {
    /** 访问地址 */
    static String url ="wss://test-perpetual-wss.hotcx.com";


    public static void main(String[] args) {
        /** 秘钥 */
        PrivateApiConfig.ACCESS_KEY="77827653e2b347fab36bfa69f2893dfb";
        PrivateApiConfig.SECRET_KEY="058B4DECEFEF68EA59BBF132617627B2";
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,null,true,true);
    }

//    send message: {"event":"signin","params":{"apiKey":"77827653e2b347fab36bfa69f2893dfb","signature":"JUtlJ7tlpwsZN1ZXZ94sTqEIOoX4N9gudJgUjJpnx/c=","timestamp":1712979624902}}
//    Received message: {"data":{"result":true},"channel":"signin"}
}
