package com.cqu.edustatistics.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.R;
import com.cqu.edustatistics.entity.StatisticsDaily;
import com.cqu.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        Integer loginNum = daily.getLoginNum();
        Integer registerNum = daily.getRegisterNum();
        Integer videoViewNum = daily.getVideoViewNum();
        Integer courseNum = daily.getCourseNum();

        return R.ok().data("loginNum",loginNum).data("registerNum",registerNum).data("videoNum",videoViewNum).data("courseNum",courseNum);


    }

}
