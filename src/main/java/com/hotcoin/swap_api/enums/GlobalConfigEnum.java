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

    TEST("d8dc89fe3c6f4f**********", "7B3D05553861740705D**********",
            "https://test-perpetual.hotcx.com", "HmacSHA256"),
    YOUR("77827653e2b347fab36bfa69f2893dfb", "058B4DECEFEF68EA59BBF132617627B2",
            "https://test-perpetual.hotcx.com", "HmacSHA256"),
    OPEN("8c80118b4d2a4a4f8a05a5451679bb3e", "AFD965CC508592553E32B72C0BDC7E84",
            "https://test-perpetual.hotcx.com", "HmacSHA256");


    final String ACCESS_KEY;
    final String SECRET_KEY;
    final String HOST;
    final String algorithm;

}
