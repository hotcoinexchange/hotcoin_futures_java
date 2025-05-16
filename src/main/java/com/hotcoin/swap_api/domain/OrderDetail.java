package com.hotcoin.swap_api.domain;

import lombok.*;

import java.util.List;

/**
 * 委托订单详情
 */

@Data
public class OrderDetail {

    /**
     * 方向
     */
    private String side;

    /**
     * 撤销时间
     */
    private Long canceledDate;

    /**
     * 创建时间
     */
    private Long createdDate;

    /**
     * 触发时间
     */
    private Long triggerDate;

    /**
     * 设置时间
     */
    private Long settingDate;

    /**
     * 触发价
     */
    private String triggerPrice;

    /**
     * 状态   1 部分成交 2 完全成交 -1 已经撤销 0 未成交（当systemType不等于12时） 0 未触发（当systemType等于12时条件单）
     */
    private Integer status;

    /**
     * 是否是历史订单
     */
    private Boolean historyOrderFlag;

    /**
     * 系统类型  10:限价 11:市价 12:条件单 13:强平单 14:爆仓单 15：爆仓 16：强减
     */
    private Integer systemType;

    /**
     * 条件单类型  0 -止盈 1-止损 2-计划委托
     */
   private String stopLimitType;

    /**
     * 委托价
     */
    private String price;

    /**
     * 委托数量
     */
    private String amount;

    /**
     * 成交数量
     */
    private String dealAmount;

    /**
     * 成交均价
     */
    private String avgPrice;

    /**
     * 杠杆
     */
    private String lever;

    /**
     * 币种
     */
    private String currencyCode;

    /**
     * 订单类型
     */
    private Integer orderPriceType;

    /**
     * 强平详情
     */
    private LiquidateExplosionDetail liquidateExplosionDetail;

    /**
     * 成交详情
     */
    private List<OrderDealDetail> dealDetails;
    /**
     * 详细仓位方向：1.开多open_long 2.开空open_short 3.平多close_long 4.平空close_short
     */
    private String detailSide;

    /**
     * 条件单id
     */
    private Long refConditionOrderId;

    /**
     * 合约明细
     */
    private ContractNavigation contract;

    /**
     * 仓位类型
     */
    private Integer positionType;

    /**
     * 收益
     */
   private String profit;

    /**
     * 订单ID
     */
    private Long orderId;


    /**
     * 面值
     */
    private String unitAmount;

}
