package com.hotcoin.api.examples.assets;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 成交记录（资产流水）查询示例 / Transaction history (asset deal record) query example.
 *
 * <p>演示如何查询账户的历史成交流水，支持按时间范围、合约和分页进行筛选。
 * Demonstrates how to query the account's historical transaction records,
 * with support for filtering by time range, contract code and pagination.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/bills/deal-record</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class DealRecordExample {

    /**
     * 请求 URL（无路径参数）/ Request URL (no path parameters).
     */
    static String uriTemplate = "/api/v1/perpetual/bills/deal-record";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 查询开始时间（毫秒时间戳）/ Query start time (millisecond timestamp) */
        pathParam.put("startDate", "1712734214561");

        /** 查询结束时间（毫秒时间戳）/ Query end time (millisecond timestamp) */
        pathParam.put("endDate", "1713339014561");

        /** 合约 CODE 筛选（小写，例如 btcusdt）/ Contract code filter (lowercase, e.g. btcusdt) */
        pathParam.put("contractCode", "btcusdt");

        /** 页码（从 1 开始）/ Page number (starts from 1) */
        pathParam.put("page", "1");

        /** 每页记录数 / Number of records per page */
        pathParam.put("pageSize", "20");

        /** 开始记录 ID（用于游标分页）/ Starting record ID (for cursor-based pagination) */
        pathParam.put("startId", "1");

        /** 结束记录 ID（用于游标分页）/ Ending record ID (for cursor-based pagination) */
        pathParam.put("endId", "10000000");

        String uri = uriTemplate;

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
