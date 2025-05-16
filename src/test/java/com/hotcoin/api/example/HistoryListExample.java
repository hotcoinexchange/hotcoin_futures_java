package com.hotcoin.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.domain.OrderBook;
import com.hotcoin.api.domain.PageData;
import com.hotcoin.api.domain.Result;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 历史委托
 *
 * @version V1.0
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
