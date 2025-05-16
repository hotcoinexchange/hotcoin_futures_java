package com.hotcoin.api.swapExample;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.swap_api.domain.OrderBook;
import com.hotcoin.swap_api.domain.PageData;
import com.hotcoin.swap_api.domain.Result;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @description: 历史委托
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class HistoryListExample {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        String contractCode = "bchusdt";
        String uri = MessageFormat.format("/api/v1/perpetual/products/{0}/history-list", contractCode);
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, params);
        Result<PageData<OrderBook>> pageDataResult = JSONObject.parseObject(result, new TypeReference<Result<PageData<OrderBook>>>() {
        });
        pageDataResult.getData().getRows().forEach(System.out::println);
    }
}
