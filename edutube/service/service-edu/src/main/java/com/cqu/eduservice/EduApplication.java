package com.cqu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fubibo
 * @create 2021-07-05 下午3:24
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.cqu"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
