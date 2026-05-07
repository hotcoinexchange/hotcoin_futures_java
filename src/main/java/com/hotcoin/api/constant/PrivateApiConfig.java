package com.hotcoin.api.constant;

/**
 * 用户私有 API 配置类（存储 AccessKey 和 SecretKey，供示例代码使用）
 * User's private API configuration — stores AccessKey and SecretKey used by example code.
 *
 * <p>温馨提示 / Tips：
 * 为了您的资金安全，请不要泄漏您的 API 秘钥，并为您的 API 绑定 IP 地址。
 * For the safety of your funds, please do not leak your API key and bind one or more
 * IP addresses to your API.</p>
 *
 * <p>安全提示 / Security notice: 使用前请将下方占位符替换为您的真实密钥，切勿将真实密钥提交至版本控制系统。
 * Replace the placeholder strings below with your real keys before running examples.
 * Never commit real credentials to version control.</p>
 *
 * @author hotcoin
 * @version 1.0.0
 * @date 2022/3/26 11:25
 */
public class PrivateApiConfig {

    /**
     * 您的 Access Key（主账号）
     * 请改为您在 Hotcoin 平台申请的 Access Key。
     * Your primary Access Key — replace with the one obtained from the Hotcoin platform.
     */
    public static String YOUR_KEY = "YOUR_ACCESS_KEY";

    /**
     * 您的 Secret Key（主账号）
     * 请改为您在 Hotcoin 平台申请的 Secret Key，并妥善保管。
     * Your primary Secret Key — replace with the one obtained from the Hotcoin platform.
     * Keep this value private and never share it.
     */
    public static String YOUR_SECRET_KEY = "YOUR_SECRET_KEY";

    /**
     * 辅助账号 Access Key 2（可选）
     * Secondary Access Key 2 (optional, leave empty if not used).
     */
    public static String QUANT_KEY = "";

    /**
     * 辅助账号 Secret Key 2（可选）
     * Secondary Secret Key 2 (optional, leave empty if not used).
     */
    public static String QUANT_SECRET_KEY = "";

    /**
     * 辅助账号 Access Key 3（可选）
     * Tertiary Access Key 3 (optional, leave empty if not used).
     */
    public static String POR_QUANT_KEY = "";

    /**
     * 辅助账号 Secret Key 3（可选）
     * Tertiary Secret Key 3 (optional, leave empty if not used).
     */
    public static String POR_QUANT_SECRET_KEY = "";
}
