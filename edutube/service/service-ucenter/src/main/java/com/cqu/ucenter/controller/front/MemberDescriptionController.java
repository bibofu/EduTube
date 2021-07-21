package com.cqu.ucenter.controller.front;

import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.MD5;
import com.cqu.commonutils.R;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.ucenter.entity.Member;
import com.cqu.ucenter.entity.Vo.UrlVo;
import com.cqu.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CGT
 * @create 2021-07-17 9:08
 */

@Api(description = "个人中心")
@RestController
@RequestMapping("/ucenter/description")
public class MemberDescriptionController {
    @Autowired
    private MemberService memberService;


    @ApiOperation(value = "获取用户信息")
    @GetMapping("updateInfo")
    public R getInfo(HttpServletRequest request){
        try {

            String id = JwtUtils.getMemberIdByJwtToken(request);
            if(StringUtils.isEmpty(id)) {
                return R.error().code(28004).message("请登录");
            }
            Member member = memberService.getById(id);

            String nickname = member.getNickname();
            String password = member.getPassword();
            String avatar = member.getAvatar();

            return R.ok().data("nickname",nickname).data("password",password).data("avatar",avatar);


        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"执行全局异常处理");
        }
    }

    @ApiOperation(value = "修改头像")
    @PostMapping("updateAvatar")
    public R updateAvatar(HttpServletRequest request, @RequestBody UrlVo url)
    {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            if(StringUtils.isEmpty(memberId)) {
                return R.error().code(28004).message("请登录");
            }

            Member member=memberService.getById(memberId);
            member.setAvatar(url.getUrl());
            memberService.updateById(member);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"修改密码失败");
        }

    }
    @ApiOperation(value = "修改昵称")
    @PostMapping("updateNickname/{nickname}")
    public R updateNickname(HttpServletRequest request,@PathVariable String nickname){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            if(StringUtils.isEmpty(memberId)) {
                return R.error().code(28004).message("请登录");
            }

            Member member=memberService.getById(memberId);
            member.setNickname(nickname);
            String token=JwtUtils.getJwtToken(memberId,nickname);
            memberService.updateById(member);
            return R.ok().data("token",token);
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"修改昵称失败");
        }
    }
    @ApiOperation(value = "修改手机号")
    @PostMapping("updateMobile/{mobile}")
    public R updateMobile(HttpServletRequest request,@PathVariable String mobile)
    {
        try{
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            if(StringUtils.isEmpty(memberId)) {
                return R.error().code(28004).message("请登录");
            }
            Member member=memberService.getById(memberId);
            member.setMobile(mobile);
            memberService.updateById(member);
            return R.ok();
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MyException(20001,"修改手机号失败");
        }
    }
    @ApiOperation(value = "修改密码")
    @PostMapping("updatePassword/{password}")
    public R updatePassword(HttpServletRequest request,@PathVariable String password){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            if(StringUtils.isEmpty(memberId)) {
                return R.error().code(28004).message("请登录");
            }

            Member member=memberService.getById(memberId);
            member.setPassword(MD5.encrypt(password));
            memberService.updateById(member);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"修改密码失败");
        }
    }
}
