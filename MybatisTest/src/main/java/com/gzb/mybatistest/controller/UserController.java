package com.gzb.mybatistest.controller;

import com.gzb.mybatistest.pojo.User;
import com.gzb.mybatistest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author feiti
 * @version 1.0
 * @description: TODO
 * @date 2026/2/8 22:08
 */
@RestController
@RequestMapping("/findAllUser")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    public List<User> findAllUser(){
        return userService.list();
    }
}
