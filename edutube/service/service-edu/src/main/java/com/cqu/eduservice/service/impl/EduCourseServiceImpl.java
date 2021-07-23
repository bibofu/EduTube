package com.cqu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.eduservice.client.StatisticsClient;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.EduCourseDescription;
import com.cqu.eduservice.entity.frontvo.CourseFrontVo;
import com.cqu.eduservice.entity.frontvo.CourseWebVo;
import com.cqu.eduservice.entity.vo.CourseInfoVo;
import com.cqu.eduservice.entity.vo.CoursePublishVo;
import com.cqu.eduservice.mapper.EduCourseMapper;
import com.cqu.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.servicebase.exceptionhandler.MyException;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private StatisticsClient statisticsClient;

    /**
     * 向课程表和课程描述表添加数据
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

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }
        if(!StringUtils.isEmpty(courseFrontVo.getPrice())){
            wrapper.gt("price",0.0);
        } else{
            wrapper.eq("price",0);
        }

        baseMapper.selectPage(pageParam,wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        CourseWebVo courseWebVo=baseMapper.getBaseCourseInfo(courseId);
        //if(courseWebVo==null)System.out.println("courseWebVo is null!!");
        return courseWebVo;
    }

    @Override
    public void removeBySubjectId(String id) {
        QueryWrapper<EduCourse>wrapper=new QueryWrapper<>();
        wrapper.eq("subject_id",id);
        EduCourse course=baseMapper.selectOne(wrapper);
        if(course!=null){
            removeCourse(course.getId());
        }
    }


    @Cacheable(key = "'IndexList'",value = "bannerlist")
    @Override
    public List<EduCourse> getNewCourse() {
        QueryWrapper<EduCourse>wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduCourse>list=baseMapper.selectList(wrapper);
        return list;
    }
    @Cacheable(key = "'hotCourse'",value = "course")
    @Override
    public List<EduCourse> getHotCourse() {
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("version");
        wrapper.last("limit 8");
        List<EduCourse> courses = baseMapper.selectList(wrapper);
        return courses;

    }


}
