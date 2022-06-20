package com.hotcoin.api.swapExample;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.swap_api.domain.Result;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @description: TODO 类描述
 * @author: hotcoin
 * @date: 2022/4/15
 **/
public class CandlesExample {
    /**
     * t:time
     * l:low
     * h:high
     * o:open
     * c:close
     * klineType 1-lastPrice 2-markPrice 3-indexPrice
     */
    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("kline", "1min");
        pathParam.put("since", "0");
        pathParam.put("size", "1000");
        pathParam.put("klineType", "2");
        String uri = "/api/v1/perpetual/public/etcusdt/candles";
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, pathParam);
        Result<Object[]> indexInfoResult = JSONObject.parseObject(result, new TypeReference<Result<Object[]>>() {
        });
        Object[] data = indexInfoResult.getData();
        for (Object datum : data) {
            JSONArray item = (JSONArray) datum;
            System.out.print("t：" + item.get(0)+"\t");
            System.out.print("l：" + item.get(1)+"\t");
            System.out.print("h：" + item.get(2)+"\t");
            System.out.print("o：" + item.get(3)+"\t");
            System.out.print("c：" + item.get(4)+"\t");
            System.out.println();

        }
    }
}
