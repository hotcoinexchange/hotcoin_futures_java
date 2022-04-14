package com.hotcoin.api.examples;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.utils.HotcoinRestConnection;
import com.hotcoin.api.utils.RandomUtils;
import com.hotcoin.api.utils.UrlBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自成交下单V1
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/4/9 18:18
 */
public class SelfMatchV1Example {

    public static void main(String[] args) {
        APIConfiguration config = APIConfiguration.buildGlobal();
        HotcoinRestConnection hotcoinRestConnection = new HotcoinRestConnection(config);
        UrlBuilder urlBuilder = UrlBuilder.build();
        String uri = "/v1/order/selfMatch";

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> orderBuy = new HashMap<>();
        orderBuy.put("symbol", "btc_usdt");
        orderBuy.put("type", "buy");
        orderBuy.put("tradePrice", "40089.77");
        orderBuy.put("tradeAmount", "0.01558");
        orderBuy.put("clientOrderId", RandomUtils.get32UUID());
        list.add(orderBuy);
        Map<String, Object> orderSell = new HashMap<>();
        orderSell.put("symbol", "btc_usdt");
        orderSell.put("type", "sell");
        orderSell.put("tradePrice", "40089.77");
        orderSell.put("tradeAmount", "0.01558");
        orderSell.put("clientOrderId", RandomUtils.get32UUID());
        list.add(orderSell);

        urlBuilder.putToPost(list);
        String result = hotcoinRestConnection.executeSignedPost(uri, urlBuilder);

        System.out.println(result);
    }

}
