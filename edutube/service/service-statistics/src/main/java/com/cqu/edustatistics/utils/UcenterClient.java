package com.cqu.edustatistics.utils;

import com.cqu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author CGT
 * @create 2021-07-15 16:13
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping(value = "/ucenter/member/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);

    @GetMapping(value = "ucenter/member/countlogin/{day}")
    public R loginCount(@PathVariable("day") String day);

    @GetMapping(value = "ucenter/member/countvideo/{day}")
    public R videoCount(@PathVariable("day")String day);

    @GetMapping(value = "ucenter/member/countcourse/{day}")
    public R courseCount(@PathVariable("day")String day);
}
