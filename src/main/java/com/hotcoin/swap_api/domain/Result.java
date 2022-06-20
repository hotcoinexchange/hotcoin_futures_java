package com.hotcoin.swap_api.domain;

import lombok.Data;

/**
 * @version V1.0
 * @description: TODO 类描述
 * @author: hotcoin
 * @date: 2022/4/15
 **/
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
