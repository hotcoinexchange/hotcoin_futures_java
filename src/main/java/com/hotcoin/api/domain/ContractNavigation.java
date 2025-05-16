package com.hotcoin.api.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * 合约明细
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractNavigation {

    /**
     * 合约code
     */
    private String code;

    /**
     * 基础货币名,如btc、fbtc
     */
    private String base;

    /**
     * 计价货币名，usd,cny,usdt
     */
    private String quote;

    /**
     * 方向 0:正向,1:反向
     */
    private Integer direction;

    /**
     * 基础货币最小交易小数位
     */
    private Integer minTradeDigit;

    /**
     * 计价货币最小交易小数位
     */
    private Integer minQuoteDigit;

    /**
     * 最新价
     */
    private String price;

    /**
     * 涨跌幅
     */
    private String fluctuation;

    /**
     * 最高价
     */
    private String high;

    /**
     * 最低价
     */
    private String low;

    /**
     * 24小时成交张数
     */
    private String amount24;

    /**
     * 24小时成交价值
     */
    private String size24;

    /**
     * 持仓量
     */
    private String totalPosition;

    /**
     * 资金费率
     */
    private String fund;

    /**
     * 标记价格
     */
    private String markPrice;

    /**
     * 指数价格
     */
    private String indexPrice;

    /**
     * 一张合约对应的quote面值,默认1
     */
    private BigDecimal unitAmount;

    /**
     * 一张合约对应的quote面值,默认1
     */
    private String unitAmountStr;

    /**
     * 是否测试盘 0:线上盘,1:测试盘
     */
    private Integer env;

    /**
     * 最大杠杆
     */
    private BigDecimal maxLever;

    /**
     * 指数货币 indexBase
     */
    private String indexBase;

    /**
     * 保证金精度
     */
    private Integer marginDigit;

    /**
     * 标记价格最小单位（精度）
     */
    private Integer marketPriceDigit;

    /**
     * 最小交易单位
     */
    private BigDecimal minTradeUnit;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 下一次清算时间差
     */
    private Long nextLiquidationInterval;

    /**
     * 开盘时间
     */
    private String openTradeTime;

    /**
     * 倒计时时间间隔
     */
    private String countDownTimeInterval;

    /**
     * 发布状态:1预发,2上线后7天内,3.上线七天后
     */
    private Integer tradeStatus;

    /**
     * 最新价折合人民币
     */
    private String lastCny;

    /**
     * taker 汇率
     */
    private String takerRate;

    /**
     * maker 汇率
     */
    private String makerRate;

}
