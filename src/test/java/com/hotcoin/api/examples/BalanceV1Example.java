package com.hotcoin.api.examples;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.utils.HotcoinRestConnection;
import com.hotcoin.api.utils.UrlBuilder;

/**
 * 账户余额
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/28 22:40
 */
public class BalanceV1Example {

    public static void main(String[] args) {
        HotcoinRestConnection hotcoinRestConnection = new HotcoinRestConnection(APIConfiguration.buildGlobal());
        UrlBuilder urlBuilder = UrlBuilder.build();
        String uri = "/v1/balance";
        String result = hotcoinRestConnection.executeSignedGet(uri, urlBuilder);

        System.out.println(result);
    }

}
