package com.hotcoin.swap_api.domain;

import lombok.*;

import java.util.Date;

/**
 * 成交详情
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
