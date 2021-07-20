package com.cqu.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CGT
 * @create 2021-07-14 11:04
 */

@Api(description = "热门课程")
@RestController
@RequestMapping("/eduservice/hotcourse")
@CrossOrigin
public class EduHotCourseController {
    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "获取热门课程")
    @GetMapping("getHotCourse")
    public R getHotCourse()
    {
//        QueryWrapper<EduCourse>wrapper=new QueryWrapper<>();
//        wrapper.orderByDesc("id");
//        wrapper.last("limit 8");
//        List<EduCourse>list=courseService.list(wrapper);

        List<EduCourse> list = courseService.getHotCourse();
        return R.ok().data("courseList",list);
    }
}
