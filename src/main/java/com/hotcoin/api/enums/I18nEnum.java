package com.hotcoin.api.enums;

/**
 * internationalization
 * 国际化语言定义
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 10:36
 */
public enum I18nEnum {

    ZH_CN("zh_CN", "简体中文"),
    EN_US("en_US", "English"),
    KO_KR("ko_KR", "한국어");

    private String name;

    private String desc;

    I18nEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
