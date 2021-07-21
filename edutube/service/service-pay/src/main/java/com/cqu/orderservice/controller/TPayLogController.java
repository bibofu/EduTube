package com.cqu.orderservice.controller;


import com.cqu.commonutils.R;
import com.cqu.orderservice.service.TPayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-16
 */
@Api(description = "订单记录")
@RestController
@CrossOrigin
@RequestMapping("/orderservice/t-pay-log")
public class TPayLogController {

    @Autowired
    private TPayLogService payLogService;

    //生成微信支付二维码,根据订单号

    @ApiOperation(value = "根据订单号生成微信支付二维码")
    @GetMapping("createNative/{orderNo}")
    private R createNative(@PathVariable String orderNo){


        Map map=payLogService.createNative(orderNo);

        return R.ok().data(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @ApiOperation(value = "根据订单号查询支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {

        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");

    }



}

