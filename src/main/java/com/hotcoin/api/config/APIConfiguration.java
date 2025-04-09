package com.hotcoin.api.config;

import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.enums.I18nEnum;
import lombok.*;

/**
 * Hotcoin API Configuration
 * Hotcoin API配置
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 11:41
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class APIConfiguration {

    /**
     * Access Key
     * {@link com.hotcoin.api.constant.PrivateApiConfig}
     */
    private String accessKey = PrivateApiConfig.HUGH_KEY;

    /**
     * Secret Key
     * {@link com.hotcoin.api.constant.PrivateApiConfig}
     */
    private String secretKey = PrivateApiConfig.HUGH_SECRET_KEY;

    /**
     * Rest api endpoint url
     * {@link com.hotcoin.api.constant.HotcoinApiUrls}
     */
    private String endpoint = HotcoinApiUrls.SPOT_REST_URL;

    /**
     * connection timeout.
     */
    private long connectTimeout = 5000;

    /**
     * read timeout.
     */
    private long readTimeout = 5000;

    /**
     * write timeout.
     */
    private long writeTimeout = 5000;

    /**
     * internationalization  {@link com.hotcoin.api.enums.I18nEnum}
     */
    private I18nEnum i18n = I18nEnum.EN_US;

    public APIConfiguration(String accessKey, String secretKey, String endpoint) {
        if (accessKey.equalsIgnoreCase(PrivateApiConfig.HUGH_KEY)){
            this.accessKey = PrivateApiConfig.HUGH_KEY;
            this.secretKey = PrivateApiConfig.HUGH_SECRET_KEY;
        }
        if (accessKey.equalsIgnoreCase(PrivateApiConfig.QUANT_KEY)){
            this.accessKey = PrivateApiConfig.QUANT_KEY;
            this.secretKey = PrivateApiConfig.QUANT_SECRET_KEY;
        }
        this.endpoint = endpoint;
    }

    /**
     * 创建全局配置
     * Create Global Configuration
     *
     * @return
     */
    public static APIConfiguration buildGlobal() {
        return new APIConfiguration();
    }

    /**
     * 创建自定义配置
     * Create Customized Configuration
     *
     * @return
     */
    public static APIConfiguration build(String accessKey, String secretKey, String endpoint) {
        return new APIConfiguration(accessKey, secretKey, endpoint);
    }

}
