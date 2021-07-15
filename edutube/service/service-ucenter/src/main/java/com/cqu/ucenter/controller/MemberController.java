package com.cqu.ucenter.controller;


import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.R;
import com.cqu.commonutils.ordervo.UcenterMemberOrder;
import com.cqu.commonutils.uservo.LoginInfoVo;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.ucenter.entity.Member;
import com.cqu.ucenter.entity.Vo.LoginVo;
import com.cqu.ucenter.entity.Vo.RegisterVo;
import com.cqu.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

    //根据token获取用户信息
    @ApiOperation("根据token获取用户详细信息")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }




    // 根据用户id获取信息
    @ApiOperation("根据id获取用户登录信息")
    @GetMapping("getUserInfo/{id}")
    public R getUserInfo(@PathVariable String id){

        LoginInfoVo info = memberService.getLoginInfo(id);

        return R.ok().data("info",info);
    }

    // 根据用户id获取订单用户信息
    @GetMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMemberOrder memberOrder=new UcenterMemberOrder();

        Member member=memberService.getById(id);
        BeanUtils.copyProperties(member,memberOrder);

        return memberOrder;
    }



}

