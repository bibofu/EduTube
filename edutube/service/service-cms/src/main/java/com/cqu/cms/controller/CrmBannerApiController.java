package com.cqu.cms.controller;

import com.cqu.cms.entity.CrmBanner;
import com.cqu.cms.service.CrmBannerService;
import com.cqu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author CGT
 * @create 2021-07-14 10:33
 */
@RestController
@RequestMapping("/cms/banner")
@CrossOrigin
@Api(description = "前端轮播图列表")
public class CrmBannerApiController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取所有轮播图")
    @GetMapping("getAllBanner")
    public R getAllBanner()
    {
        List<CrmBanner>bannerList=bannerService.getAllBanner();
        return R.ok().data("bannerList",bannerList);
    }
}
