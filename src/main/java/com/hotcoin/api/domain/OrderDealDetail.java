package com.hotcoin.api.domain;

import lombok.*;

import java.util.Date;

/**
 * 成交详情
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDealDetail {

    /**
     * 成交时间
     */
    private Date dealDate;

    /**
     * 成交数量
     */
    private String dealAmount;

    /**
     * 手续费
     */
    private String fee;

}
