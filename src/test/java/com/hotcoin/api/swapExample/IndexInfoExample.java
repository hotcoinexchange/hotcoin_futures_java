package com.hotcoin.api.swapExample;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.swap_api.domain.IndexInfo;
import com.hotcoin.swap_api.domain.Result;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;

/**
 * @version V1.0
 * @description: IndexInfoExample
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
