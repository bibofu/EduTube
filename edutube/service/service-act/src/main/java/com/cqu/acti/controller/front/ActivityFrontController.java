package com.cqu.acti.controller.front;

import com.cqu.acti.entity.EduActivity;
import com.cqu.acti.service.EduActivityService;
import com.cqu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CGT
 * @create 2021-07-22 15:05
 */
@RestController
@RequestMapping("/acti/front")
public class ActivityFrontController {
    @Autowired
    private EduActivityService activityService;

    @ApiOperation(value = "获取活动")
    @GetMapping("getAll")
    public R getAll()
    {
        List<EduActivity>list=activityService.list(null);
        return R.ok().data("ActList",list);
    }

}
