package com.hotcoin.api.domain;

import lombok.Data;

import java.util.Date;

/**
 * 订单
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
public class OrderBook {

    /**
     * ID
     */
    private Long id;

    /**
     * 合约Code
     */
    private String contractCode;

    /**
     * 基础货币名,如btc
     */
    private String base;

    /**
     * 计价货币名，usd,cny,usdt
     */
    private String quote;

    /**
     * 指数货币
     */
    private String indexBase;

    /**
     * 方向 0:正向,1:反向
     */
    private Integer contractDirection;

    /**
     * 仓位方向，long多，short空
     */
    private String side;

    /**
     * 仓位方向 1.开多open_long 2.开空open_short 3.平多close_long 4.平空close_short
     */
    private String detailSide;

    /**
     * 委托数量
     */
    private String amount;

    /**
     * 已成交数量
     */
    private String dealAmount;

    /**
     * 平均成交价格
     */
    private String avgPrice;

    /**
     * 委托价格
     */
    private String price;

    /**
     * 委托价值
     */
    private String orderSize;

    /**
     * 0 等待成交 1 部分成交 2 已经成交 -1 已经撤销
     */
    private Integer status;

    /**
     * 10:限价 11:市价 13:强平单 14:爆仓单 15：穿仓 16：强减
     */
    private Integer systemType;

    /**
     * 触发方向，greater大于，less小于
     */
    private String direction;

    /**
     * 该笔订单成交后对应的盈亏: 正表示盈利,负表示亏损
     */
    private String profit;

    /**
     * 该笔订单成交后交的手续费: 正表示得手续费,负表示付手续费
     */
    private String fee;

    /**
     * 该笔订单取消的理由，0是默认值，1 系统撤销，2 用户取消 其他-委托失败
     */
    private Integer reason;

    /**
     * 计划委托类型: index-指数价格market/mark-标记价格mark/last-最新价格last
     */
    private String triggerBy;

    /**
     * 触发价格
     */
    private String triggerPrice;

    /**
     * 触发时间
     */
    private Long triggerDate;

    /**
     * 最后操作时间
     */
    private Long modifyDate;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 来源
     */
    private String source;

    /**
     * 关联的条件订单id
     */
    private Long refConditionOrderId;

    /**
     * 关联条件单
     */
    private OrderCondition refOrderCondition;

    /**
     * 条件单类型  0 -止盈 1-止损 2-计划委托
     */
    private String stopLimitType;

    /**
     * 杠杆
     */
    private String lever;

    /**
     * 20 最优20档/10 最优10档/5 最优5档/1 对手价
     */
    private Integer orderPriceType;

    /**
     * 持仓类型
     */
    private Integer positionType;

    /**
     * 一张合约对应的quote面值,默认1
     */
    private String unitAmount;

    /**
     * 保证金小数位
     */
    private Integer marginDigit;

    /**
     * 标记价格小数位
     */
    private Integer marketPriceDigit;

    /**
     * 关联止损单
     */
    private OrderCondition stopLoss;

    /**
     * 关联止盈单
     */
    private OrderCondition stopProfit;

}
