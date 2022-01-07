package com.learn.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description:
 * date: 2021/3/9 21:01
 * Package: com.learn.ucenter
 *
 * @author 李佳乐
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"com.learn"})
@MapperScan("com.learn.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
