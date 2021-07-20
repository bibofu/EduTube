package com.cqu.eduservice.client;

import com.cqu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CGT
 * @create 2021-07-16 16:12
 */
@Component
@FeignClient(name = "service-statistics",fallback = StatisticsClientFallback.class)
public interface StatisticsClient {

    @GetMapping("/edustatistics/front/updateCourseNum")
    public R updateCourseNum();
}
