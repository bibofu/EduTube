package com.cqu.eduservice.controller;


import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduSubject;
import com.cqu.eduservice.service.EduSubjectService;
import com.cqu.eduservice.subject.OneSubject;
import com.cqu.servicebase.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-06
 */

@Api(description = "课程分类")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //1. 添加课程分类
    @ApiOperation(value = "添加课程分类")
    @PostMapping("addSubject")
    public R add(MultipartFile file){

        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    //2. 课程分类列表
    @GetMapping("getAllSubject")
    public R getAll(){

        List<OneSubject> subjectList=subjectService.getSubjectInfo();

        return R.ok().data("subjectList",subjectList);

    }

    @ApiOperation(value = "添加一个课程分类")
    @PostMapping("addOneSubject")
    public R addOneSubject(@RequestBody EduSubject eduSubject)
    {
        subjectService.save(eduSubject);
        return R.ok();
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        EduSubject subject = subjectService.getById(id);
        if (subject.getParentId().equals("0")){
            return R.error().data("info","有下级分类，无法删除");
        }else{
            subjectService.removeById(id);
            return R.ok();
        }

    }



}

