package com.hotcoin.api.domain;

import lombok.Data;

/**
 * 响应结果
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Data
public class Result<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态描述
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

}
