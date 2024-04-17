package com.hotcoin.api.examples.trading;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 历史委托D
 *
 * @author zenghaihui
 * @date 2024/4/16
 */
public class HistoryListExample {

    /**
     * 请求url模版
     */
    static String uriTemplate = "/api/v1/perpetual/products/{0}/history-list";

    public static void main(String[] args) {
        Map<String, String> pathParam = new HashMap<>();
        /** 基础币 */
        pathParam.put("base", "usdt");
        /** 仓位方向 1.开多open_long 2.开空open_short 3.平多close_long 4.平空close_short	 */
        pathParam.put("detailSide", "open_long");
        /** 状态：1 已成交 2 部分成交已撤销 3 已撤单；多个条件过滤用","分隔	 */
        pathParam.put("status", "0");
        /** 合约方向 0:正向,1:反向 */
        pathParam.put("type", "10");
        /** 下单类型：10:限价 11:市价 13:强平单 14:爆仓单 15：穿仓 16：强减;多个条件过滤用","分隔 */
        pathParam.put("systemType", "10");
        /** 开始时间	 */
        pathParam.put("startDate", "1712734214561");
        /** 结束时间	 */
        pathParam.put("endTime", "1713339014561");
        /** 第几页	 */
        pathParam.put("page", "1");
        /** 页面大小	 */
        pathParam.put("pageSize", "20");

        /** 路径参数：合约code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");
        String result = HttpUtil.get(GlobalConfigEnum.HUGH, uri, pathParam);
        System.out.println(result);
    }
}
