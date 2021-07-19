package com.cqu.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduTeacher;
import com.cqu.eduservice.service.EduTeacherService;
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
 * @create 2021-07-14 10:59
 */

@Api(description = "热门讲师")
@RestController
@RequestMapping("/eduservice/hotteacher")
@CrossOrigin
public class EduHotTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "获取热门讲师")
    @GetMapping("getHotTeacher")
    public R getHotTeacher()
    {
//        QueryWrapper<EduTeacher>wrapper=new QueryWrapper<>();
//        wrapper.orderByDesc("id");
//        wrapper.last("limit 4");
//        List<EduTeacher> list=teacherService.list(wrapper);

        List<EduTeacher> teacher = teacherService.getHotTeacher();
        return R.ok().data("teacherList",teacher);
    }
}
