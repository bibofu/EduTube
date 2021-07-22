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
import com.cqu.ucenter.utils.StatisticsClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Date date=new Date();
        String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String day=sdf.format(date);


        String token = memberService.login(loginVo,day);
        return R.ok().data("token",token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo)
    {
        Date date=new Date();
        String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String day=sdf.format(date);
        memberService.register(registerVo,day);
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
    @ApiOperation(value = "根据token获取用户详细信息")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        Member member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }



    //用于课程评论时返回用户信息
    @ApiOperation(value = "评论时返回用户信息")
    @GetMapping("getCommentInfo/{id}")
    public LoginInfoVo getUcenterInfo(@PathVariable String id){

        LoginInfoVo info = memberService.getLoginInfo(id);

        System.out.println(info.getNickname());
        System.out.println(info.getAvatar());

        return info;



    }

    // 根据用户id获取信息
    @ApiOperation("根据id获取用户登录信息")
    @GetMapping("getUserInfo/{id}")
    public R getUserInfo(@PathVariable String id){

        LoginInfoVo info = memberService.getLoginInfo(id);

        return R.ok().data("info",info);
    }

    @GetMapping(value = "countregister/{day}")
    public R registerCount(@PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }
    @GetMapping(value = "countlogin/{day}")
    public R countLogin(@PathVariable String day){
        Integer count=memberService.countLoginByDay(day);
        return R.ok().data("countLogin",count);
    }
    @GetMapping(value = "countvideo/{day}")
    public R countVideo(@PathVariable String day){
        Integer count=memberService.countVideoByDay(day);
        return R.ok().data("countVideo",count);
    }
    @GetMapping(value = "countcourse/{day}")
    public R countCourse(@PathVariable String day){
        Integer count=memberService.countCourseByDay(day);
        return R.ok().data("countCourse",count);
    }
    // 根据用户id获取订单用户信息
    @ApiOperation(value = "根据用户id获取订单用户信息")
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
        UcenterMemberOrder memberOrder=new UcenterMemberOrder();

        Member member=memberService.getById(id);
        BeanUtils.copyProperties(member,memberOrder);

        return memberOrder;
    }

}

