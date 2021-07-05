package com.cqu.eduservice.controller;


import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduTeacher;
import com.cqu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-05
 */

@Api(description="讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;



    //1. 查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R finaAll(){

        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("teacher",list);

    }

    //2. 删除讲师
    @ApiOperation(value = "删除讲师")
    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){

        boolean b = teacherService.removeById(id);
        if (b){
            return R.ok();

        }else{
            return R.error();
        }


    }



}

