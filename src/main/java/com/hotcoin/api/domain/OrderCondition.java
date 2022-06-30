package com.hotcoin.api.domain;

import lombok.*;

import java.util.Date;

/**
 * 条件单
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
public class OrderCondition {

    /**
     * 条件单id
     */
    private Long id;

    /**
     * 是Base和Quote之间的组合
     */
    private String contractCode;

    /**
     * 基础货币名,如btc、fbtc
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
     * 触发类型：指数价格，标记价格，最新价格
     */
    private String type;

    /**
     * 触发方向，greater大于，less小于
     */
    private String direction;

    /**
     * 触发价格
     */
    private String triggerPrice;

    /**
     * 计划委托类型: index-指数价格market/mark-标记价格mark/last-最新价格last
     */
    private String triggerBy;

    /**
     * 用户订单委托或者破产价格
     */
    private String price;
    /**
     * 仓位方向，long多，short空
     */
    private String side;

    /**
     * 1.开多open_long 2.开空open_short 3.平多close_long 4.平空close_short
     */
    private String detailSide;

    /**
     * 委托数量
     */
    private String amount;

    /**
     * 平均成交价格
     */
    private String avgPrice;

    /**
     * 已成交数量
     */
    private String dealAmount;

    /**
     * 委托价值
     */
    private String orderSize;

    /**
     * 已经成交价值
     */
    private String dealSize;

    /**
     * 状态  1:已触发 0、2：未触发 3：预条件单 -1：已撤销
     */
    private Integer status;

    /**
     * 订单被撤销原因  1：系统撤销  2：用户撤销  3、4、5：触发后委托失败  中间用逗号分割
     */
    private Integer reason;

    /**
     * 10:限价 11:市价 13:强平单 14:爆仓单
     */
    private Integer systemType;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 来源
     */
    private String source;

    /**
     * 下单类型
     * 如果需要设置下单类型，则price字段只能为空串或不传，下单价格类型，对手价：opponent，最优20档：optimal_20，最优10档：optimal_10，最优5档：optimal_5
     */
    private Integer orderPriceType;

    /**
     * 仓位模式
     */
    private Integer positionType;

    /**
     * 条件类型
     */
    private Integer stopLimitType;

    private Long refOrderId;

    /**
     * 杠杆
     */
    private Integer lever;

    /**
     * 单位
     */
    private String unitAmount;

    /**
     * 保证金位数
     */
    private Integer marginDigit;

    /**
     * 价格位数
     */
    private Integer marketPriceDigit;

    /**
     * 触发时间
     */
    private Long triggerDate;

    /**
     * 修改时间
     */
    private Long modifyDate;

    private TriggerOrderDetail tiggerOrderDetail;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TriggerOrderDetail {

        private Long id;

        private String price;

    }

}

