package com.cqu.edustatistics.controller.front;

import com.cqu.commonutils.R;
import com.cqu.edustatistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CGT
 * @create 2021-07-16 15:58
 */
@RestController
@EnableFeignClients
@RequestMapping("/edustatistics/front")
@CrossOrigin
public class DailyFrontController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @GetMapping("updateVideoViewNum")
    public R updateVideoViewNum()
    {
        Date date=new Date();
        String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String day=sdf.format(date);
        statisticsDailyService.updateVideo(day);
        return R.ok();
    }

    @GetMapping("updateCourseNum")
    public R updateCourseNum()
    {
        Date date=new Date();
        String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String day=sdf.format(date);
        statisticsDailyService.updateCourse(day);
        return R.ok();
    }


}
