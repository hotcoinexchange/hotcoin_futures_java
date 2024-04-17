package com.hotcoin.swap_api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @version V1.0
 * @description: TODO 类描述
 * @author: hotcoin
 * @date: 2022/4/15
 **/
@Getter
@AllArgsConstructor
public enum HttpMethodEnum {
    DELETE("DELETE"),
    GET("GET"),
    POST("POST");
    final String method;
}
