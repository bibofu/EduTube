package com.cqu.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fubibo
 * @create 2021-07-05 下午3:20
 */

@Configuration
@MapperScan("com.cqu.eduservice.mapper")
public class EduConfig {

    /**
    逻辑删除插件
     */

    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }


    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
