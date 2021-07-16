package com.cqu.ucenter.service;

import com.cqu.commonutils.uservo.LoginInfoVo;
import com.cqu.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqu.ucenter.entity.Vo.LoginVo;
import com.cqu.ucenter.entity.Vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo,String date);

    void register(RegisterVo registerVo);

    LoginInfoVo getLoginInfo(String memberId);

    Member getByOpenId(String openid);

    Integer countRegisterByDay(String day);

    Integer countLoginByDay(String day);

    Integer countVideoByDay(String day);

    Integer countCourseByDay(String day);
}
