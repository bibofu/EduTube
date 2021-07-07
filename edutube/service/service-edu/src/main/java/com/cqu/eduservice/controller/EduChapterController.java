package com.cqu.eduservice.controller;


import com.cqu.commonutils.R;
import com.cqu.eduservice.entity.EduChapter;
import com.cqu.eduservice.entity.chapter.ChapterVo;
import com.cqu.eduservice.service.EduChapterService;
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

@Api(description = "章节管理")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {


    @Autowired
    private EduChapterService chapterService;

    //1.根据课程id获取章节、小节

    @ApiOperation("根据id查询课程章节小节")
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterV(@PathVariable String courseId){

        List<ChapterVo> list = chapterService.getChapterVideoById(courseId);

        return R.ok().data("chapterList",list);


    }

    //2 添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }



    //3 根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapter(@PathVariable String chapterId){

        EduChapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",chapter);
    }



    //4 修改章节
    @PostMapping("updateChapter")
    public R update(@RequestBody EduChapter chapter){

        chapterService.updateById(chapter);

        return R.ok();
    }

    //5 删除章节
    @DeleteMapping("{chapterId}")
    public R delete(@PathVariable String chapterId){

        boolean flag = chapterService.deleteChapter(chapterId);

        if (flag){
            return R.ok();

        }else{

            return R.error();

        }


    }




}

