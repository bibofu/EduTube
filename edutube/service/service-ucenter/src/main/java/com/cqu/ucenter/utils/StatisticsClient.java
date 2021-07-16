package com.cqu.ucenter.utils;

import com.cqu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CGT
 * @create 2021-07-16 14:55
 */
@Component
@FeignClient("service-statistics")
public interface StatisticsClient {
    @GetMapping(value = "/eduservice/statistics/updateLoginNum/{day}")
    public R updateLoginNum(@PathVariable("day") String day);

    @GetMapping(value = "/eduservice/statistics/updateRegisterNum/{day}")
    public R updateRegisterNum(@PathVariable("day")String day);
}
