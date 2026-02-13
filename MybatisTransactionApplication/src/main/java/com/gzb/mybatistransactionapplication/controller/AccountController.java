package com.gzb.mybatistransactionapplication.controller;

import com.gzb.mybatistransactionapplication.pojo.Account;
import com.gzb.mybatistransactionapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/13 18:24
 */

@RestController
public class AccountController {

    @SuppressWarnings("all")
    @Autowired
    AccountService accountService;

    @GetMapping("/")
    public Account getAccount() {
        //查询账户
        return accountService.getAccount();
    }

    @GetMapping("/add")
    public Object addMoney() {
        try {
            accountService.addMoney();
        } catch (Exception e) {
            return "发生异常了：" + accountService.getAccount();
        }
        return getAccount();
    }

}
