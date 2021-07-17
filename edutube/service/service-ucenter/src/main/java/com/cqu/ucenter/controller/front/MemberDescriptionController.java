package com.cqu.ucenter.controller.front;

import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.MD5;
import com.cqu.commonutils.R;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.ucenter.entity.Member;
import com.cqu.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CGT
 * @create 2021-07-17 9:08
 */
@RestController
@RequestMapping("/ucenter/description")
public class MemberDescriptionController {
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "修改头像")
    @PostMapping("updateAvatar/{url}")
    public R updateAvatar(HttpServletRequest request, @PathVariable String url)
    {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            Member member=memberService.getById(memberId);
            member.setAvatar(url);
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
    @ApiOperation(value = "修改密码")
    @PostMapping("updatePassword/{password}")
    public R updatePassword(HttpServletRequest request,@PathVariable String password){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            Member member=memberService.getById(memberId);
            member.setPassword(MD5.encrypt(password));
            memberService.updateById(member);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"修改密码");
        }
    }
}
