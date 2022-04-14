package com.hotcoin.api.bean.basic;

/**
 * 交易对信息
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 13:51
 */
public class Currency {

    private String currency;

    private String name;

    private Boolean canWithdraw;

    private Boolean canDeposit;

    private String minWithdrawal;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public String getMinWithdrawal() {
        return minWithdrawal;
    }

    public void setMinWithdrawal(String minWithdrawal) {
        this.minWithdrawal = minWithdrawal;
    }

}
