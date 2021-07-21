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
        LoginInfoVo vo=new LoginInfoVo();
        vo.setNickname("404");
        vo.setAvatar("https://z3.ax1x.com/2021/07/17/WQ6ETK.jpg");
        return vo;
    }
}
