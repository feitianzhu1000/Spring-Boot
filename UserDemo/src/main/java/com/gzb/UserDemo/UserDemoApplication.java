package com.gzb.UserDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.gzb.UserDemo.dao")
@ComponentScan(basePackages= {"com.gzb.UserDemo"})

//打开定时任务
@EnableScheduling
public class UserDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDemoApplication.class, args);
	}

}
