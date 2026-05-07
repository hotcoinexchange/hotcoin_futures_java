package com.hotcoin.api.examples.market;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * K 线（蜡烛图）数据查询示例 / Candlestick (K-line) data query example.
 *
 * <p>演示如何查询指定合约的历史 K 线数据，支持多种时间粒度。
 * Demonstrates how to retrieve historical candlestick data for a given contract,
 * supporting multiple time granularities.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/public/{contractCode}/candles</p>
 *
 * @author Hugh
 * @date 2025/8/20 18:35
 */
public class PublicCandlesExpample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/public/{0}/candles";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** K 线时间粒度（如 1min / 5min / 15min / 30min / 60min / 4hour / 1day 等）
         *  Candlestick interval (e.g. 1min, 5min, 15min, 30min, 60min, 4hour, 1day) */
        pathParam.put("kline", "1min");

        /** 返回 K 线数量 / Number of candlesticks to return */
        pathParam.put("size", "50");

        /** K 线数据类型（1=标准 K 线）/ Candlestick data type (1=standard candlestick) */
        pathParam.put("klineType", "1");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
