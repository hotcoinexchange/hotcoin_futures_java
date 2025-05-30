package com.hotcoin.swap_api.domain;

import lombok.Data;

/**
 * @version V1.0
 * @description: OpenApi 市场行情数据DTO
 * @author: hotcoin
 * @date: 2022/4/12
 **/
@Data
public class PremiumIndex {
    /**
     * 合约Code
     */
    private String contractCode;
    /**
     * 计价货币
     */
    private String quoteCurrency;
    /**
     * 交易货币
     */
    private String baseCurrency;
    /**
     * 标记价
     */
    private String markPrice;
    /**
     * 指数价
     */
    private String indexPrice;
    /**
     * 最近更新的资金费率
     */
    private String lastFeeRate;
    /**
     * 预估的资金费率
     */
    private String estimateFeeRate;
    /**
     * 最新交易价格
     */
    private String lastPrice;
    /**
     * 总持仓量
     */
    private String totalPosition;
    /**
     * 更新时间
     */
    private Long time;
}
