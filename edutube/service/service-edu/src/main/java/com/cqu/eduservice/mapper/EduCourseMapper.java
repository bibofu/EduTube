package com.cqu.eduservice.mapper;

import com.cqu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqu.eduservice.entity.frontvo.CourseWebVo;
import com.cqu.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);


    public CourseWebVo getBaseCourseInfo(String courseId);

}
