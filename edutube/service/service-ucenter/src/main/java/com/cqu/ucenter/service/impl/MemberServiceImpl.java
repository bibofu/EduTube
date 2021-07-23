package com.cqu.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.MD5;
import com.cqu.commonutils.uservo.LoginInfoVo;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.ucenter.entity.Member;
import com.cqu.ucenter.entity.Vo.LoginVo;
import com.cqu.ucenter.entity.Vo.RegisterVo;
import com.cqu.ucenter.mapper.MemberMapper;
import com.cqu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.ucenter.utils.StatisticsClient;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private StatisticsClient statisticsClient;

    @Override
    public String login(LoginVo loginVo,String date) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ) {
            throw new MyException(20001,"电话号码或者密码为空");
        }
        //获取会员
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if(null == member) {
            throw new MyException(20001,"用户不存在");
        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new MyException(20001,"密码错误");
        }
        //校验是否被禁用
        if(member.getIsDisabled()) {
            throw new MyException(20001,"账户被禁用");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        statisticsClient.updateLoginNum(date);
        return token;
    }

    @Override
    public void register(RegisterVo registerVo,String date) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if( StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new MyException(20001,"电话号码、密码或者验证码为空");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobleCode)) {
            throw new MyException(20001,"验证码错误");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new MyException(20001,"手机号已被注册");
        }
        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://z3.ax1x.com/2021/07/17/WQ6ETK.jpg");
        statisticsClient.updateRegisterNum(date);
        this.save(member);
    }

    @Override
    public LoginInfoVo getLoginInfo(String memberId) {
        Member member = baseMapper.selectById(memberId);
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        BeanUtils.copyProperties(member, loginInfoVo);
        return loginInfoVo;
    }

    @Override
    public Member getByOpenId(String openid) {
        QueryWrapper<Member>wrapper=new QueryWrapper<>();
        wrapper.eq("openid",openid);
        Member member=baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }

    @Override
    public Integer countLoginByDay(String day) {
        return baseMapper.selectLoginCount(day);
    }

    @Override
    public Integer countVideoByDay(String day) {
        return baseMapper.selectVideoCount(day);
    }

    @Override
    public Integer countCourseByDay(String day) {
        return baseMapper.selectCourseCount(day);
    }

}
