package com.hotcoin.api.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.domain.Result;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 合约成交数据
 *
 * @version V1.0
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class FillsExample {

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        String contractCode = "btcusdt";
        String uri = MessageFormat.format("/api/v1/perpetual/public/{0}/fills", contractCode);
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, params);
        Result<Object[]> fillResult = JSONObject.parseObject(result, new TypeReference<Result<Object[]>>() {
        });
        Object[] data = fillResult.getData();
        for (Object datum : data) {
            JSONArray item = (JSONArray) datum;
            //price
            System.out.print("p：" + item.get(0) + "\t");
            //amount
            System.out.print("a：" + item.get(1) + "\t");
            //side
            System.out.print("s：" + item.get(2) + "\t");
            //time
            System.out.print("t：" + item.get(3) + "\t");
            //id
            System.out.print("ID：" + item.get(4) + "\t");
            System.out.println();
        }
    }

}
