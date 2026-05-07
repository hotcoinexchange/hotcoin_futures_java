package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量下单示例 / Place batch orders example.
 *
 * <p>演示如何在一次请求中同时提交多笔委托单。
 * Demonstrates how to submit multiple orders in a single request.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/products/{contractCode}/batch-order</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class BatchOrderExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/batch-order";

    public static void main(String[] args) {

        // ---- 第一笔订单参数 / First order parameters ----
        Map<String, String> oneParam = new HashMap<>();
        /** 订单类型（10 限价 / 11 市价）/ Order type (10=limit, 11=market) */
        oneParam.put("type", "11");
        /** 方向（open_long 开多 / open_short 开空 / close_long 平多 / close_short 平空）
         *  Side (open_long, open_short, close_long, close_short) */
        oneParam.put("side", "open_long");
        /** 委托价格 / Order price */
        oneParam.put("price", "11");
        /** 委托数量 / Order quantity */
        oneParam.put("amount", "1");
        /** 被动委托标志（0 不被动 / 1 被动）/ Post-only flag (0=off, 1=on) */
        oneParam.put("beMaker", "0");
        /** IOC 标志（0 关闭 / 1 开启：未成交部分立即取消）
         *  IOC flag (0=off, 1=on: unfilled portion cancelled immediately) */
        oneParam.put("ioc", "1");
        /** 自定义标签 / Custom tag for order tracking */
        oneParam.put("tag", "1");

        // ---- 第二笔订单参数 / Second order parameters ----
        Map<String, String> twoParam = new HashMap<>();
        /** 订单类型（10 限价 / 11 市价）/ Order type (10=limit, 11=market) */
        twoParam.put("type", "11");
        /** 方向 / Side */
        twoParam.put("side", "open_long");
        /** 委托价格 / Order price */
        twoParam.put("price", "11");
        /** 委托数量 / Order quantity */
        twoParam.put("amount", "1");
        /** 被动委托标志 / Post-only flag */
        twoParam.put("beMaker", "0");
        /** IOC 标志 / IOC flag */
        twoParam.put("ioc", "1");
        /** 自定义标签 / Custom tag */
        twoParam.put("tag", "1");

        // 将两笔订单封装到列表中 / Wrap both orders into a list
        List<Map<String, String>> pathParam = new ArrayList<>();
        pathParam.add(oneParam);
        pathParam.add(twoParam);

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, new HashMap(), pathParam);
        System.out.println(result);
    }
}
