package com.hotcoin.api.examples.push;

import com.alibaba.fastjson.JSON;
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
        params.put("type","assets");
        /** 模拟交易 */
        params.put("env",1);
        /** 是否序列化 */
        params.put("serialize",false);
        pushMsg.put("params",params);
        return JSON.toJSONString(pushMsg);
    }

    public static void main(String[] args) {
        /** 调用远程WebSocket */
        WebSocketUtil.webConnect(url,paramsGenerate(),true,true);
    }

//    send message: {"event":"subscribe","params":{"serialize":false,"biz":"perpetual","type":"assets","env":"1"}}
//    Received message: {"biz":"perpetual","data":{"result":true},"channel":"subscribe","type":"assets","env":1}
//    Received message: {"biz":"perpetual","data":{"totalCnyAccountRights":"72297.92","totalBtcUnRealizedSurplus":"0","data":[{"followRealizedSurplus":"0.000000","bizType":"perpetual","cnPrice":"72282.67","appLogo":"https://hotcoin-hk-static.oss-accelerate.aliyuncs.com/hotcoin/upload/coin/a417aec2fc85428aafed802649e227caUSDT.png","realizedSurplusValue":"0.000000","fee":"0.000000","orderFee":"0.226125","realAvailableBalance":"9997.889500","realizedSurplus":"0.000000","availableBalance":"9997.889500","positionAccountRights":"10000.000000","maintenanceMargin":"0.000000","rate":[],"price":"9997.88","realPositionAccountRights":"0.000000","currencyCodeDisplayName":"USDT","accountRightsEthValue":"5.213247","unRealizedSurplusBtcValue":"0.000000","unRealizedSurplusValue":"0.000000","accountRightsValue":"10000.000000","welfareTotalProfit":0,"openMargin":"0.000000","frozenBalance":"0.000000","followUnRealizedSurplus":"0.000000","sort":3,"env":1,"accountRightsBtcValue":"0.147733","welfareTotalProfitIn24Hours":0,"accountRightsCnValue":"72297.92","decimalPlaces":6,"currentOrderMargin":"2.110500","webLogo":"https://hotcoin-hk-static.oss-accelerate.aliyuncs.com/hotcoin/upload/coin/a417aec2fc85428aafed802649e227caUSDT.png","risk":"0","unRealizedSurplus":"0.000000","orderMargin":"2.110500","positionMargin":"0.000000","currencyCode":"F_USDT","availableMargin":"9997.889500","btcValue":"0.14770267"}],"totalBtcAccountRights":"0.147733","totalBtcAvaliableMargins":"0.14770267","userId":100400312},"type":"assets","env":1,"timestamp":1713021899069}

}
