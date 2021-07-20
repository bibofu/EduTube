package com.cqu.eduservice.client;

import com.cqu.commonutils.uservo.LoginInfoVo;
import org.springframework.stereotype.Component;

/**
 * @author fubibo
 * @create 2021-07-15 上午11:23
 */

@Component
public class UcenterClientFallback implements UcenterClient{


    @Override
    public LoginInfoVo getUcenterInfo(String memberId) {
        return null;
    }
}
