package com.cqu.eduservice.controller;


import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.vo.CourseInfoVo;
import com.cqu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //1. 添加课程基本信息方法
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){

        courseService.saveCourseInfo(courseInfoVo);

        return R.ok();


    }

    //2. 根据课程id查询课程信息
    @ApiOperation(value = "根据id查询课程信息")
    @GetMapping("getCourseInfo/{courseId}")
    public R getInfo(@PathVariable String courseId){

        CourseInfoVo list=courseService.getCourseInfo(courseId);

        return R.ok().data("courseInfo",list);
    }

    //3. 修改课程信息
    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }





}

