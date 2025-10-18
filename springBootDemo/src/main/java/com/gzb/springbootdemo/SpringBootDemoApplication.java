package com.gzb.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        System.out.println("【日志监控】测试");
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
