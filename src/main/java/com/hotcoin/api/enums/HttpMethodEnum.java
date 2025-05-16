package com.hotcoin.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * HTTP 请求方法定义
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Getter
@AllArgsConstructor
public enum HttpMethodEnum {

    GET("GET"),
    POST("POST");

    final String method;

}
