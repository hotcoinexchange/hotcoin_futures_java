package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.utils.WebSocketUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author zenghaihui
 * @date 2024/4/12
 */
public class OrderExample {
    /** 访问地址 */
    static String url ="wss://test-perpetual-wss.hotcx.com";

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
        params.put("type","orders");
        /** 模拟交易 */
        params.put("env",1);
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

//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","type":"orders","env":1}}
//    Received message: {"biz":"perpetual","data":{"result":true},"channel":"subscribe","type":"orders","env":1}
//    Received message: {"biz":"perpetual","data":{"reason":0,"orderType":0,"positionType":0,"contractType":"perpetual","fee":"0.000000","quoteDisplayName":"USDT","liquidationDate":1713033900000,"mode":0,"quote":"f_usdt","price":"3768.750000","indexBase":"f_eth","id":3248719562556096,"traderNickname":"","isNftPhoto":0,"modifyDate":1713022106113,"dealAmount":"0","contractDirection":0,"liquidatePrice":"","deductedFee":"0","lever":20,"indexBaseDisplayName":"ETH","reduceOnly":0,"selectedMode":0,"unitAmount":"0.01","status":0,"extraMargin":"0","bizType":"perpetual","avgPrice":"0.000000","marginDigit":6,"orderSize":"75.375","orderFee":"0.452250","dualPosition":1,"baseDisplayName":"USDT","marketPriceDigit":6,"contractCodeDisplayName":"ETHUSDT","systemType":11,"dealMarket":0,"profit":"0.000000","side":"long","amount":"2","brokerId":1,"openMargin":"3.76875","dealSize":"0","traderPhoto":"","avgMargin":"1.884375","userId":100400312,"mustMaker":0,"detailSide":"open_long","createdDate":1713022106113,"positionId":2809149,"ioc":0,"contractCode":"f_ethf_usdt","clazz":0,"base":"f_usdt"},"type":"orders","env":1,"timestamp":1713022106139}
//    Received message: {"biz":"perpetual","data":{"reason":0,"orderType":0,"positionType":0,"contractType":"perpetual","fee":"-0.045000","quoteDisplayName":"USDT","liquidationDate":1713033900000,"mode":0,"quote":"f_usdt","price":"3768.750000","indexBase":"f_eth","id":3248719562556096,"traderNickname":"","isNftPhoto":0,"modifyDate":1713022106113,"dealAmount":"2","contractDirection":0,"liquidatePrice":"","deductedFee":"0","lever":20,"indexBaseDisplayName":"ETH","reduceOnly":0,"selectedMode":0,"unitAmount":"0.01","status":2,"extraMargin":"0","bizType":"perpetual","avgPrice":"3750.000000","marginDigit":6,"orderSize":"75.375","orderFee":"0.452250","dualPosition":1,"baseDisplayName":"USDT","marketPriceDigit":6,"contractCodeDisplayName":"ETHUSDT","systemType":11,"dealMarket":0,"profit":"0.000000","side":"long","amount":"2","brokerId":1,"openMargin":"3.76875","dealSize":"75","traderPhoto":"","avgMargin":"1.884375","userId":100400312,"mustMaker":0,"detailSide":"open_long","createdDate":1713022106113,"positionId":2809149,"ioc":0,"contractCode":"f_ethf_usdt","clazz":0,"base":"f_usdt"},"type":"orders","env":1,"timestamp":1713022106187}

}
