package com.hotcoin.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.domain.OrderDetail;
import com.hotcoin.api.domain.Result;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单详情
 *
 * @version V1.0
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class OrderDetailExample {

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("orderId", "138427205741776");
        String contractCode = "bchusdt";
        String uri = MessageFormat.format("/api/v1/perpetual/products/{0}/orderDetail", contractCode);
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, params);
        Result<OrderDetail> detailResult = JSONObject.parseObject(result, new TypeReference<Result<OrderDetail>>() {
        });
        System.out.println(detailResult.getData());
    }

}