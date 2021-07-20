package com.cqu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.commonutils.R;
import com.cqu.eduservice.client.StatisticsClient;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.EduHistory;
import com.cqu.eduservice.entity.EduTeacher;
import com.cqu.eduservice.entity.vo.CourseInfoVo;
import com.cqu.eduservice.entity.vo.CoursePublishVo;
import com.cqu.eduservice.entity.vo.CourseQuery;
import com.cqu.eduservice.service.EduCourseService;
import com.cqu.eduservice.service.EduHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */

@Api(description = "课程操作")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@EnableFeignClients
public class EduCourseController {

    @Autowired
    private StatisticsClient statisticsClient;
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduHistoryService historyService;

    //1. 添加课程基本信息方法
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){

        String id = courseService.saveCourseInfo(courseInfoVo);
        String title = courseInfoVo.getTitle();

        EduHistory history=new EduHistory();
        history.setDescription("添加课程: "+title);
        historyService.save(history);




        return R.ok().data("id",id);


    }

    //2. 根据课程id查询课程信息
    @ApiOperation(value = "根据id查询课程信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getInfo(@PathVariable String courseId){

        CourseInfoVo list=courseService.getCourseInfo(courseId);

        EduCourse course = courseService.getById(courseId);
        String title = course.getTitle();
        EduHistory history=new EduHistory();
        history.setDescription("查询课程: "+title+" id为: "+courseId);
        historyService.save(history);


        return R.ok().data("courseInfo",list);
    }

    //3. 修改课程信息
    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);

        String title = courseInfoVo.getTitle();
        EduHistory history=new EduHistory();
        history.setDescription("修改课程: "+title);
        historyService.save(history);

        return R.ok();
    }

    //4. 获取发布时课程信息
    @ApiOperation(value = "获取课程publish前信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublish=courseService.coursePublishInfo(id);
        return R.ok().data("coursePublish",coursePublish);
    }

    //5. 课程最终发布，修改课程状态
    @ApiOperation("发布课程，改变发布状态")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态

        String title = eduCourse.getTitle();
        EduHistory history=new EduHistory();
        history.setDescription("发布课程: "+title);
        historyService.save(history);

        statisticsClient.updateCourseNum();
        courseService.updateById(eduCourse);
        return R.ok();
    }

    //6. 获取课程信息
    @ApiOperation(value = "获取课程列表")
    @GetMapping
    public R getCourse(){

        List<EduCourse> courseList = courseService.list(null);
        return R.ok().data("courseList",courseList);
    }


    //7. 删除课程
    @ApiOperation(value = "根据课程id删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);

        EduCourse course = courseService.getById(courseId);
        String title = course.getTitle();
        EduHistory history=new EduHistory();
        history.setDescription("删除课程: "+title);
        historyService.save(history);

        return R.ok();
    }

    @ApiOperation(value = "条件分页查询课程")
    @PostMapping("pageConditionCourse/{current}/{limit}")
    public R pageConditionCourse(@PathVariable long current, @PathVariable long limit,@RequestBody(required = false) CourseQuery courseQuery)
    {
        Page<EduCourse> page=new Page<>(current,limit);
        QueryWrapper<EduCourse>queryWrapper=new QueryWrapper<>();
        String name=courseQuery.getName();
        if(!StringUtils.isEmpty(name))
        {
            queryWrapper.like("title",name);
        }
        courseService.page(page,queryWrapper);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    //增加课程热度方法
    @ApiOperation(value = "增加课程热度")
    @PostMapping("addhot/{courseId}")
    public R addhot(@PathVariable String courseId){

        EduCourse course = courseService.getById(courseId);
        Long hot = course.getVersion();
        hot=hot+1;
        course.setVersion(hot);
        courseService.updateById(course);

        return R.ok();

    }


}

