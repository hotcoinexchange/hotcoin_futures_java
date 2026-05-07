package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 历史委托订单列表示例 / Historical order list example.
 *
 * <p>演示如何查询已完成（成交、撤销等）的历史委托记录，支持多种过滤条件。
 * Demonstrates how to query historical orders (filled, cancelled, etc.)
 * with various filter parameters.</p>
 *
 * <p>接口 / Endpoint: GET /api/v1/perpetual/products/{contractCode}/history-list</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class HistoryListExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/history-list";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();

        /** 基础货币（如 usdt）/ Base currency (e.g. usdt) */
        pathParam.put("base", "usdt");

        /** 仓位方向筛选（open_long 开多 / open_short 开空 / close_long 平多 / close_short 平空）
         *  Position side filter (open_long, open_short, close_long, close_short) */
        pathParam.put("detailSide", "open_long");

        /** 状态筛选（1=已成交 / 2=部分成交已撤销 / 3=已撤单；多个值用","分隔）
         *  Status filter (1=filled, 2=partially filled then cancelled, 3=cancelled; use "," for multiple) */
        pathParam.put("status", "0");

        /** 合约方向（0=正向合约 / 1=反向合约）/ Contract direction (0=direct, 1=inverse) */
        pathParam.put("type", "10");

        /** 下单类型（10=限价 / 11=市价 / 13=强平 / 14=爆仓 / 15=穿仓 / 16=强减；多个值用","分隔）
         *  Order type (10=limit, 11=market, 13=force-close, 14=liquidation, 15=bankruptcy, 16=reduce-only;
         *  use "," for multiple) */
        pathParam.put("systemType", "10");

        /** 查询开始时间（毫秒时间戳）/ Query start time (millisecond timestamp) */
        pathParam.put("startDate", "1712734214561");

        /** 查询结束时间（毫秒时间戳）/ Query end time (millisecond timestamp) */
        pathParam.put("endTime", "1713339014561");

        /** 页码（从 1 开始）/ Page number (starts from 1) */
        pathParam.put("page", "1");

        /** 每页记录数 / Number of records per page */
        pathParam.put("pageSize", "20");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.get(GlobalConfigEnum.YOUR, uri, pathParam);
        System.out.println(result);
    }
}
