package com.cqu.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.EduTeacher;
import com.cqu.eduservice.service.EduCourseService;
import com.cqu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author CGT
 * @create 2021-07-15 14:52
 */

@Api(description = "讲师前台")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @ApiOperation("分页查询讲师")
    @PostMapping("getTeacherFrontList/{current}/{limit}")
    public R getTeacherFrontList(@PathVariable long current,@PathVariable long limit)
    {
        Page<EduTeacher> teacherPage=new Page<>(current,limit);
        Map<String,Object> map=teacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }

    @ApiOperation("获取讲师详情")
    @GetMapping("getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable String id)
    {
        EduTeacher eduTeacher = teacherService.getById(id);
        QueryWrapper<EduCourse>wrapper=new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> list = courseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",list);
    }
}
