package com.hotcoin.api.example;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.api.domain.PremiumIndex;
import com.hotcoin.api.domain.Result;
import com.hotcoin.api.enums.GlobalConfigEnum;
import com.hotcoin.api.utils.HttpUtil;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

/**
 * 最新标记价格和资金费率
 *
 * @version V1.0
 * @author: hotcoin
 * @date: 2022/4/16
 **/
public class PremiumIndexExample {

    public static void main(String[] args) {
        Map<String, String> params = Collections.emptyMap();
        String contractCode = "btcusdt";
        String uri = MessageFormat.format("/api/v1/perpetual/public/{0}/premiumIndex", contractCode);
        String result = HttpUtil.get(GlobalConfigEnum.TEST, uri, params);
        Result<PremiumIndex> premiumIndexResult = JSONObject.parseObject(result, new TypeReference<Result<PremiumIndex>>() {
        });
        System.out.println(premiumIndexResult.getData());
    }

}
