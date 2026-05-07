package com.hotcoin.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局 API 配置枚举（保存 AccessKey、SecretKey、Host 和签名算法）
 * Global API configuration enum holding access key, secret key, host and signing algorithm.
 *
 * <p>使用前请将 YOUR_ACCESS_KEY 和 YOUR_SECRET_KEY 替换为您在 Hotcoin 平台申请的真实密钥。
 * Before use, replace YOUR_ACCESS_KEY and YOUR_SECRET_KEY with the real credentials
 * obtained from the Hotcoin platform.</p>
 *
 * <p>安全提示 / Security notice: 请勿将真实密钥提交到版本控制系统。
 * Never commit real API credentials to version control.</p>
 *
 * @author hotcoin
 * @version 1.0.0
 * @date 2022/3/26 10:36
 */
@Getter
@AllArgsConstructor
public enum GlobalConfigEnum {

    /**
     * 测试环境配置，请替换为您的真实密钥
     * Test environment configuration — replace with your real credentials.
     */
    TEST("YOUR_ACCESS_KEY", "YOUR_SECRET_KEY", "https://api-ct.hotcoin.fit", "HmacSHA256"),
    ;

    /**
     * 您在 Hotcoin 平台申请的 Access Key（访问密钥）
     * Access key obtained from the Hotcoin platform (identifies your account).
     */
    final String ACCESS_KEY;

    /**
     * 您在 Hotcoin 平台申请的 Secret Key（签名密钥，请妥善保管）
     * Secret key obtained from the Hotcoin platform (used for HMAC signing — keep it private).
     */
    final String SECRET_KEY;

    /**
     * Hotcoin 永续合约 REST API 的 Host 地址
     * Host address for the Hotcoin Perpetual Futures REST API.
     */
    final String HOST;

    /**
     * 签名算法名称，固定为 HmacSHA256
     * Signing algorithm name, always HmacSHA256.
     */
    final String algorithm;
}
