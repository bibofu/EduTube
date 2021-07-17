package com.cqu.act;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CGT
 * @create 2021-07-14 10:13
 */
@SpringBootApplication
@ComponentScan({"com.cqu"})
@MapperScan("com.cqu.cms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
