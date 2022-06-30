package com.hotcoin.api.domain;

import lombok.Data;

import java.util.List;

/**
 * 指数价成分信息
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
public class IndexInfo {

    /**
     * 币种名称
     */
    private String index;

    /**
     * 指数价格
     */
    private String indexPrice;

    /**
     * 时间
     */
    private Long time;

    /**
     * 成分
     */
    private List<Component> components;

    @Data
    public static class Component {
        /**
         * 交易所名称
         */
        private String name;

        /**
         * 收集价格
         */
        private String price;

        /**
         * 权重
         */
        private String wgt;

        /**
         * 币对
         */
        private String symbol;
    }

}
