package com.cqu.ucenter.controller;


import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.R;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.ucenter.entity.Vo.LoginInfoVo;
import com.cqu.ucenter.entity.Vo.LoginVo;
import com.cqu.ucenter.entity.Vo.RegisterVo;
import com.cqu.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */

@Api(description = "会员服务")
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员登陆")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo)
    {
        String token = memberService.login(loginVo);
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo)
    {
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "获取登录信息")
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request)
    {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoVo loginInfoVo = memberService.getLoginInfo(memberId);
            return R.ok().data("item",loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"error");
        }
    }
}

