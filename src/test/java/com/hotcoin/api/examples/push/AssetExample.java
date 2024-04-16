package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 资产
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class AssetExample {
    /** 访问地址 */
    static String url ="wss://wss-ct.hotcoin.fit";

    /**
     * 请求参数制造方法
     * @return
     */
    static String paramsGenerate(){
        Map<String,Object> pushMsg = new HashMap<>();
        /** 请求类型 */
        pushMsg.put("event","subscribe");
        Map<String,Object> params = new HashMap<>();
        /** 业务类型 */
        params.put("biz","perpetual");
        /** 资产 */
        params.put("type","assets");
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 秘钥 */
        PrivateApiConfig.ACCESS_KEY="77827653e2b347fab36bfa69f2893dfb";
        PrivateApiConfig.SECRET_KEY="058B4DECEFEF68EA59BBF132617627B2";
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),true,true);
    }

}
