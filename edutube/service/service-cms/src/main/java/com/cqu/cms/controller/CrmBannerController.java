package com.cqu.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqu.cms.entity.CrmBanner;
import com.cqu.cms.service.CrmBannerService;
import com.cqu.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */
@RestController
@RequestMapping("/eduservice/banner")
@CrossOrigin
public class CrmBannerController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "分页获取轮播图列表")
    @GetMapping("pageBanner/{current}/{limit}")
    public R pageBanner(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                        @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit)
    {
        Page<CrmBanner> bannerPage=new Page<>(current,limit);
        bannerService.page(bannerPage,null);
        return R.ok().data("items",bannerPage.getRecords()).data("totals",bannerPage.getTotal());
    }

    @ApiOperation(value = "添加轮播图")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner)
    {
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取轮播图")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id)
    {
        CrmBanner banner=bannerService.getById(id);
        return R.ok().data("items",banner);
    }

    @ApiOperation(value = "修改轮播图")
    @PostMapping("updateById/{id}")
    public R updateById(@RequestBody CrmBanner banner)
    {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除轮播图")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id)
    {
        bannerService.removeById(id);
        return R.ok();
    }
}

