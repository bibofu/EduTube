package com.cqu.eduservice.service.impl;

import com.cqu.eduservice.entity.CourseCollect;
import com.cqu.eduservice.mapper.CourseCollectMapper;
import com.cqu.eduservice.service.CourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-20
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

    @Override
    public void collect(String memberId, String courseId) {

        CourseCollect collect=new CourseCollect();
        collect.setMemberId(memberId);
        collect.setCourseId(courseId);
        baseMapper.insert(collect);

    }
}
