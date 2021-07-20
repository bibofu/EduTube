package com.cqu.edustatistics.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.R;
import com.cqu.edustatistics.entity.StatisticsDaily;
import com.cqu.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author CGT
 * @create 2021-07-17 9:02
 */
@RestController
@CrossOrigin
@RequestMapping("/edustatistics/cart")
public class showCart {
    @Autowired
    private StatisticsDailyService dailyService;

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(@PathVariable String begin, @PathVariable String
            end, @PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }

    @ApiOperation(value = "获取统计数据")
    @GetMapping("showdata/{day}")
    public R getData(@PathVariable String day){

        QueryWrapper<StatisticsDaily> wrapper=new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily = dailyService.getOne(wrapper);
        Integer loginNum,registerNum,videoViewNum,courseNum;
        if(daily!=null) {
            loginNum = daily.getLoginNum();
            registerNum = daily.getRegisterNum();
            videoViewNum = daily.getVideoViewNum();
            courseNum = daily.getCourseNum();
        }
        else{
            daily=new StatisticsDaily();
            daily.setLoginNum(0);
            daily.setRegisterNum(0);
            daily.setCourseNum(0);
            daily.setVideoViewNum(0);
            daily.setDateCalculated(day);
            loginNum = daily.getLoginNum();
            registerNum = daily.getRegisterNum();
            videoViewNum = daily.getVideoViewNum();
            courseNum = daily.getCourseNum();
            dailyService.save(daily);
        }
        return R.ok().data("loginNum",loginNum).data("registerNum",registerNum).data("videoNum",videoViewNum).data("courseNum",courseNum);
    }

    @ApiOperation(value = "查询15天")
    @GetMapping("findFifteen")
    public R findFifteen()
    {
        Date date=new Date();
        String pattern="yyyy-MM-dd";
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        String end=sdf.format(date);

        Calendar cal = Calendar.getInstance();
        //获取前面的时间用-负号
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -14);
        String begin=sdf.format(cal.getTime());
        Map<String,List<Integer>>map=dailyService.getFifteen(begin,end);
        return R.ok().data("total",map.size()).data("map",map);
    }
}
