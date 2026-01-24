package com.gzb.helloworlddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@SpringBootApplication
@Controller
public class HelloWorldDemoApplication {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("【日志监控】进入方法");
        return "Hello World";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldDemoApplication.class, args);
    }

}
