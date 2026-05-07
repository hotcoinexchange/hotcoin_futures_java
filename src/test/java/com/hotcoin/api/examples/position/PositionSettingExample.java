package com.hotcoin.api.examples.position;

import com.hotcoin.swap_api.enums.GlobalConfigEnum;
import com.hotcoin.swap_api.util.HttpUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置自动追加保证金示例 / Set auto-add margin (auto margin replenishment) example.
 *
 * <p>演示如何开启或关闭指定合约仓位的自动追加保证金功能。
 * 开启后，当仓位接近强平线时，系统将自动从可用余额中补充保证金。
 * Demonstrates how to enable or disable automatic margin replenishment for a position.
 * When enabled, the system automatically adds margin from available balance
 * when the position approaches the liquidation price.</p>
 *
 * <p>接口 / Endpoint: POST /api/v1/perpetual/position/{contractCode}/setting</p>
 *
 * @author hugh
 * @date 2024/4/16
 */
public class PositionSettingExample {

    /**
     * 请求 URL 模版（{0} 为合约 CODE）/ Request URL template ({0} is the contract code).
     */
    static String uriTemplate = "/api/v1/perpetual/position/{0}/setting";

    public static void main(String[] args) {
        Map<String, String> bodyParam = new HashMap<>();

        /** 自动追加保证金开关（1=开启 / 0=关闭）/ Auto-add margin toggle (1=enable, 0=disable) */
        bodyParam.put("value", "1");

        /** 路径参数：合约 CODE / Path param: contract code */
        String uri = MessageFormat.format(uriTemplate, "BTCUSDT");

        /** 调用 API / Call the API */
        String result = HttpUtil.post(GlobalConfigEnum.YOUR, uri, bodyParam, bodyParam);
        System.out.println(result);
    }
}
