package com.cqu.edustatistics.controller;


import com.cqu.commonutils.R;
import com.cqu.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author CGT
 * @since 2021-07-15
 */
@RestController
@EnableFeignClients
@CrossOrigin
@RequestMapping("/edustatistics/statistics")
public class StatisticsDailyController {
    @Autowired
    StatisticsDailyService statisticsDailyService;
    @ApiOperation(value = "生成统计数据")
    @PostMapping("{day}")
    public R createStatisticsByDate(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }



    @ApiOperation(value = "登陆时修改表中登录人数")
    @PostMapping("updateLoginNum/{day}")
    public R updateLoginNum(@PathVariable String day)
    {
        statisticsDailyService.updateLoginNum(day);
        return R.ok();
    }
    @ApiOperation(value = "注册时修改表中注册人数")
    @PostMapping("updateRegisterNum/{day}")
    public R updateRegisterNum(@PathVariable String day)
    {
        statisticsDailyService.updateRegisterNum(day);
        return R.ok();
    }
}

