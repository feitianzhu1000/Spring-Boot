package com.example.scheduletask.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author dwt
 * @version V1.0
 * @Package com.example.scheduletask.util
 * @date 2026/4/18 21:14
 * @description:
 */
@Configuration
@EnableScheduling
@Slf4j
public class myTask {
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1(){
        log.info("定时任务1启动>>>>>>>>>>>>>");
    }
}
