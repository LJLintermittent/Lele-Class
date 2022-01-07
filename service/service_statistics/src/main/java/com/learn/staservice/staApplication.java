package com.learn.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description:
 * date: 2021/3/13 16:09
 * Package: com.learn.staservice
 *
 * @author 李佳乐
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages ={"com.learn"})
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("com.learn.staservice.mapper")
public class staApplication {
    public static void main(String[] args) {
        SpringApplication.run(staApplication.class, args);
    }
}
