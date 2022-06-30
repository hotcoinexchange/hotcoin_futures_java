package com.hotcoin.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 强平详情
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiquidateExplosionDetail {

    /**
     * 杠杠
     */
    private String lever;

    /**
     * 委托价格
     */
    private String price;

    /**
     * 强平价
     */
    private String liquidatePrice;

    /**
     * 破产价
     */
    private String explosionPrice;

    /**
     * 合约code
     */
    private String contractCode;

    /**
     * 手续费率
     */
    private String feeRate;

    /**
     * 维持保证金率
     */
    private String maintainRate;

    /**
     * 方向
     */
    private String side;

}
