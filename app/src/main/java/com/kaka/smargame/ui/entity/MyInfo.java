package com.kaka.smargame.ui.entity;

/**
 * Created by Administrator on 2017/2/25.
 */
public class MyInfo {
    private String accountId;
    private String accountName;
    private String userPass;

    @Override
    public String toString() {
        return "MyInfo{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
