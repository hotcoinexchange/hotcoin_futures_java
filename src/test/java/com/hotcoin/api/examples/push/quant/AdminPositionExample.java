package com.hotcoin.api.examples.push.quant;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 单个行情
 *
 * @author zenghaihui
 * @date 2024/4/10
 */
@Slf4j
public class AdminPositionExample {

    /**
     *
     * 请求参数制造方法
     */
    static String paramsGenerate() {
        Map<String, Object> pushMsg = new HashMap<>();

        /** 请求类型 */
        pushMsg.put("event", "subscribe");
        Map<String, Object> params = new HashMap<>();
        /** 业务类型 */
        params.put("biz", "perpetual");
        /** 是否序列化 */
        params.put("serialize", false);
        /** 订阅项 */
        params.put("type", "admin_position_simple");
        pushMsg.put("params", params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(HotcoinApiUrls.TEST_URL, paramsGenerate(), PrivateApiConfig.QUANT_KEY, false);
//        WebSocketUtil.webConnect(HotcoinApiUrls.PRO_URL, paramsGenerate(), PrivateApiConfig.POR_QUANT_KEY, false);
    }
}
