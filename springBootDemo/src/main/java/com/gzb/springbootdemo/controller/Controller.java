package com.gzb.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dwt
 * @Date:2025/10/19 11:23
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class Controller {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return"hello world";
    }

}
