package com.cqu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.R;
import com.cqu.commonutils.ordervo.CourseWebVoOrder;
import com.cqu.eduservice.client.OrdersClient;
import com.cqu.eduservice.entity.EduCourse;
import com.cqu.eduservice.entity.chapter.ChapterVo;
import com.cqu.eduservice.entity.frontvo.CourseFrontVo;
import com.cqu.eduservice.entity.frontvo.CourseWebVo;
import com.cqu.eduservice.service.EduChapterService;
import com.cqu.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author fubibo
 * @create 2021-07-15 下午2:22
 */

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

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoById(courseId);

        boolean buyCourse = ordersClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);
    }





    // 根据课程id查询课程信息
    @GetMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){

        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);

        CourseWebVoOrder courseWebVoOrder=new CourseWebVoOrder();

        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);

        return courseWebVoOrder;
    }


}