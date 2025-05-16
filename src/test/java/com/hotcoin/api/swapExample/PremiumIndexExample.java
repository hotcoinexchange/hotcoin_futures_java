package com.hotcoin.api.swapExample;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hotcoin.swap_api.domain.PremiumIndex;
import com.hotcoin.swap_api.domain.Result;
import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;


import java.text.MessageFormat;
import java.util.Collections;
import java.util.Map;

/**
 * @version V1.0
 * @description: 最新标记价格和资金费率
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
