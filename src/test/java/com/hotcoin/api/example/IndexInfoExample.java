package com.hotcoin.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.domain.IndexInfo;
import com.hotcoin.api.domain.Result;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.util.HashMap;

/**
 * 指数价成分信息
 *
 * @version V1.0
 * @author: hotcoin
 * @date: 2022/4/15
 **/
public class IndexInfoExample {

    public static void main(String[] args) {
        String uri = "/api/v1/perpetual/public/btcusdt/indexInfo";
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, new HashMap<>());
        Result<IndexInfo> indexInfoResult = JSONObject.parseObject(result, new TypeReference<Result<IndexInfo>>() {
        });
        System.out.println(indexInfoResult);
    }

}
