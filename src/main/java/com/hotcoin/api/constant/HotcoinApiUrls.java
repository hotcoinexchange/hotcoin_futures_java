package com.hotcoin.api.constant;

/**
 * Hotcoin Api Urls
 * Hotcoin Api 域名
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:39
 */
public interface HotcoinApiUrls {

    /**
     * spot rest api url
     * 现货Rest Api域名地址
     */
    String SPOT_REST_URL = "https://hkdevapi.hotcx.com";

    /**
     * spot websocket api url
     * 现货WebSocket Api域名地址
     */
    String SPOT_WS_URL = "wss://wss.hotcoinfin.com/trade/multiple";


    String TEST_URL = "wss://test-perpetual-wss.hotcx.com";

    String DEV_URL = "wss://dev-perpetual-wss.hotcx.com";

    String PRO_URL = "wss://wss-ct.hotcoin.fit";
}
