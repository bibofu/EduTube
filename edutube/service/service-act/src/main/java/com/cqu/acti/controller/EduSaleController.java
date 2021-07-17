package com.cqu.acti.controller;


import com.cqu.acti.entity.EduSale;
import com.cqu.acti.service.EduSaleService;
import com.cqu.commonutils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 打折表 前端控制器
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/acti/edu-sale")
public class EduSaleController {
    @Autowired
    private EduSaleService saleService;

    @ApiOperation(value = "添加优惠")
    @PostMapping("addSale")
    public R addSale(@RequestBody EduSale sale)
    {
        saleService.save(sale);
        return R.ok();
    }

    @ApiOperation(value = "修改优惠")
    @PostMapping("updateSale")
    public R updateSale(@RequestBody EduSale sale)
    {
        saleService.updateById(sale);
        return R.ok();
    }
}

