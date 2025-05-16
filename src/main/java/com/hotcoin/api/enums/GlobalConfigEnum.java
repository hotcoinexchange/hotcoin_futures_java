package com.hotcoin.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局配置
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
@Getter
@AllArgsConstructor
public enum GlobalConfigEnum {

    TEST("d8dc89fe3c6f4f**********", "7B3D055538******************", "https://api-ct.hotcoin.fit", "HmacSHA256"),
    ;

    final String ACCESS_KEY;

    final String SECRET_KEY;

    final String HOST;

    final String algorithm;

}
