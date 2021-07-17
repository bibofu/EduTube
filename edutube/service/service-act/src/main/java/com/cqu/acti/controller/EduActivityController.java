package com.cqu.acti.controller;


import com.cqu.acti.entity.EduActivity;
import com.cqu.acti.service.EduActivityService;
import com.cqu.acti.service.EduSaleService;
import com.cqu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 活动 前端控制器
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/acti/edu-activity")
public class EduActivityController {
    @Autowired
    private EduActivityService activityService;
    @Autowired
    private EduSaleService saleService;
    @ApiOperation(value = "添加活动")
    @PostMapping("addActivity")
    public R addActivity(@RequestBody EduActivity activity)
    {
        String id=activityService.saveActivity(activity);
        return R.ok().data("id",id);
    }

    @ApiOperation(value = "删除活动")
    @DeleteMapping("deleteActivity/{id}")
    public R deleteActivity(@PathVariable String id)
    {
        saleService.removeByActId(id);
        activityService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "修改活动")
    @PostMapping("updateActivity")
    public R updateActivity(@RequestBody EduActivity activity){
        activityService.updateById(activity);
        return R.ok();
    }
}

