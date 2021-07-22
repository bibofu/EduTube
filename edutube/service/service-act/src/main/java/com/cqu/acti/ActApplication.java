package com.cqu.acti;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CGT
 * @create 2021-07-22 14:52
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.cqu"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.cqu.acti.mapper")
public class ActApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActApplication.class,args);
    }

}
