package com.cqu.eduservice.service;

import com.cqu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqu.eduservice.entity.vo.CourseInfoVo;
import com.cqu.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo coursePublishInfo(String id);

    void removeCourse(String courseId);
}
