package com.cqu.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.R;
import com.cqu.commonutils.ordervo.CourseWebVoOrder;
import com.cqu.eduservice.client.OrdersClient;
import com.cqu.eduservice.entity.CourseCollect;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.chapter.ChapterVo;
import com.cqu.eduservice.entity.frontvo.CourseFrontVo;
import com.cqu.eduservice.entity.frontvo.CourseWebVo;
import com.cqu.eduservice.entity.vo.CourseQuery;
import com.cqu.eduservice.service.CourseCollectService;
import com.cqu.eduservice.service.EduChapterService;
import com.cqu.eduservice.service.EduCourseService;
import com.cqu.servicebase.exceptionhandler.MyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fubibo
 * @create 2021-07-15 下午2:22
 */

@Api(description = "课程前台")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/courseFront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    @Autowired
    private CourseCollectService collectService;

    //1 条件查询带分页查询课程
    @ApiOperation(value = "条件查询带分页课程")
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @ApiOperation(value = "查看课程详情")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoById(courseId);
        String memberId=JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(memberId)){
            return R.error().code(28004).message("请登录");
        }

        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberId);


        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }





    // 根据课程id查询课程信息
    @ApiOperation(value = "根据课程id查看课程信息(订单)")
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){

        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);

        CourseWebVoOrder courseWebVoOrder=new CourseWebVoOrder();

        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);

        return courseWebVoOrder;
    }

    @ApiOperation(value = "获取前4新的课程的轮播图")
    @GetMapping("getNewCourse")
    public R getNewCourse()
    {
        List<EduCourse>list=courseService.getNewCourse();

        return R.ok().data("courseList",list);

    }

    @ApiOperation(value = "收藏课程")
    @PostMapping("collect/{courseId}")
    public R collect(@PathVariable String courseId,HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }

        QueryWrapper<CourseCollect> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        wrapper.eq("course_id",courseId);
        int count = collectService.count(wrapper);
        if (count>0){
            return R.ok().message("课程不能重复收藏");
        }else {
            collectService.collect(memberId,courseId);
        }
        return R.ok();


    }

    @ApiOperation(value = "查看用户购买课程")
    @GetMapping("getBuy")
    public R getBuy(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        List<String> courseIdList=ordersClient.findCourse(memberId);

        List<EduCourse> courseList=new ArrayList<>();

        for(int i=0;i<courseIdList.size();i++){
            String id=courseIdList.get(i);
            EduCourse course=courseService.getById(id);
            courseList.add(course);
        }
        return R.ok().data("courseBuyList",courseList);
    }

    @ApiOperation(value = "查看用户收藏的课程")
    @GetMapping("getCollect")
    public R getCollect(HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }

        QueryWrapper<CourseCollect> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        List<CourseCollect> courseCollectList = collectService.list(wrapper);

        List<EduCourse> list=new ArrayList<>();
        for(CourseCollect collect:courseCollectList){

            String id = collect.getCourseId();
            EduCourse course = courseService.getById(id);
            list.add(course);

        }

        return R.ok().data("collect",list);

    }

    //判断用户是否已经收藏某一门课程
    @GetMapping("hascollect/{courseId}")
    @ApiOperation(value = "判断用户是否已经收藏某一门课程")
    public R hasCollect(@PathVariable String courseId,HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }

        QueryWrapper<CourseCollect> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        wrapper.eq("course_id",courseId);

        int count = collectService.count(wrapper);
        if (count>0){
            return R.ok().data("hascollect",1);
        }else{
            return R.ok().data("hascollect",0);
        }


    }

    //取消收藏
    @ApiOperation(value = "取消收藏")
    @PostMapping("cancelcollect/{courseId}")
    public R cacel(@PathVariable  String courseId,HttpServletRequest request){

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if(StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }

        QueryWrapper<CourseCollect> wrapper=new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        wrapper.eq("course_id",courseId);


        collectService.remove(wrapper);

        return R.ok();



    }
}
