package com.gzb.mybatistransactionapplication.pojo;

import java.math.BigDecimal;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/13 18:29
 */
public class Account {
    private String accountId;
    private String accountName;
    private BigDecimal balance;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
