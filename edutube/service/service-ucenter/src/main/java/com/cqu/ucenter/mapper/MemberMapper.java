package com.cqu.ucenter.mapper;

import com.cqu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */


public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterCount(String day);
    Integer selectLoginCount(String day);

    Integer selectVideoCount(String day);

    Integer selectCourseCount(String day);
}
