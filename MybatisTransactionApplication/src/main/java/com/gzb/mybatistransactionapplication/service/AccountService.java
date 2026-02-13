package com.gzb.mybatistransactionapplication.service;

import com.gzb.mybatistransactionapplication.mapper.AccountMapper;
import com.gzb.mybatistransactionapplication.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/13 18:27
 */
@Service
public class AccountService {

    @SuppressWarnings("all")
    @Autowired
    AccountMapper accountMapper;

    public Account getAccount() {
        return accountMapper.getAccount();
    }

    @Transactional
    public void addMoney() throws Exception {
        //先增加余额
        accountMapper.addMoney();
        //然后遇到故障
        throw new RuntimeException("发生异常了..");
    }
}
