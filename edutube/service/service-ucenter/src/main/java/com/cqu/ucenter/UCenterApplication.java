package com.cqu.ucenter;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CGT
 * @create 2021-07-14 15:34
 */

@ComponentScan(basePackages = {"com.cqu"})
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.cqu.ucenter.mapper")
public class UCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }
}
