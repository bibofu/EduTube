package com.cqu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author fubibo
 * @create 2021-07-06 上午11:12
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.cqu"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
