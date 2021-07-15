package com.cqu.eduservice.service.impl;

import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.EduCourseDescription;
import com.cqu.eduservice.entity.vo.CourseInfoVo;
import com.cqu.eduservice.entity.vo.CoursePublishVo;
import com.cqu.eduservice.mapper.EduCourseMapper;
import com.cqu.eduservice.service.EduChapterService;
import com.cqu.eduservice.service.EduCourseDescriptionService;
import com.cqu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.eduservice.service.EduVideoService;
import com.cqu.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;


    /**
     * 向课程表和课程描述表添加数据
     *
     * @param courseInfoVo
     */
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new MyException(20001, "添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;


    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse course = baseMapper.selectById(courseId);

        CourseInfoVo vo = new CourseInfoVo();
        BeanUtils.copyProperties(course, vo);

        EduCourseDescription description = courseDescriptionService.getById(courseId);
        vo.setDescription(description.getDescription());

        return vo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {

        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new MyException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);

    }

    @Override
    public CoursePublishVo coursePublishInfo(String id) {

        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {

        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) { //失败返回
            throw new MyException(20001, "删除失败");
        }


    }


}
