package com.cqu.eduservice.client;

import com.cqu.commonutils.uservo.LoginInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fubibo
 * @create 2021-07-15 上午11:22
 */

@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientFallback.class)
public interface UcenterClient {

    //根据用户id获取用户登录信息
    @GetMapping("/ucenter/member/getCommentInfo/{id}")
    public LoginInfoVo getUcenterInfo(@PathVariable("id") String id);
}
