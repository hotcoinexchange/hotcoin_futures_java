package com.hotcoin.api.examples.trading;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量下单
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class BatchOrderExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/batch-order";

    public static void main(String[] args) {
        Map<String, String> oneParam = new HashMap<>();
        /** 类型（10 限价 11 市价） */
        oneParam.put("type", "11");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        oneParam.put("side", "open_long");
        /** 价格 */
        oneParam.put("price", "11");
        /** 数量 */
        oneParam.put("amount", "1");
        /** 被动委托：0:不被动委托 1:被动委托 */
        oneParam.put("beMaker", "0");
        /** 订单若不能立即成交则未成交的部分立即取消（0: 关闭，1: 开启） */
        oneParam.put("ioc", "1");
        /** 标签 */
        oneParam.put("tag", "1");
        Map<String, String> twoParam = new HashMap<>();
        /** 类型（10 限价 11 市价） */
        twoParam.put("type", "11");
        /** 方向（open_long 开多 open_short 开空 close_long 平多 close_short 平空） */
        twoParam.put("side", "open_long");
        /** 价格 */
        twoParam.put("price", "11");
        /** 数量 */
        twoParam.put("amount", "1");
        /** 被动委托：0:不被动委托 1:被动委托 */
        twoParam.put("beMaker", "0");
        /** 订单若不能立即成交则未成交的部分立即取消（0: 关闭，1: 开启） */
        twoParam.put("ioc", "1");
        /** 标签 */
        twoParam.put("tag", "1");

        List<Map<String, String>> pathParam = new ArrayList<>();
        pathParam.add(oneParam);
        pathParam.add(twoParam);

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        //        String result = HttpUtil.post(GlobalConfigEnum.HUGH, uri, pathParam);
        //        System.out.println(result);
    }
}
