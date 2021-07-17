package com.cqu.edustatistics.controller.front;

import com.cqu.commonutils.R;
import com.cqu.edustatistics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
