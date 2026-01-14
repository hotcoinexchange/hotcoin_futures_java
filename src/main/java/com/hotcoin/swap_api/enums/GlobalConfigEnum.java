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
public enum GlobalConfigEnum {

    YOUR("77827653e2b347fab36bfa69f2893dfb", "058B4DECEFEF68EA59BBF132617627B2",
            "https://binn.adffhttct.com", "HmacSHA256");


    final String ACCESS_KEY;
    final String SECRET_KEY;
    final String HOST;
    final String algorithm;

}
