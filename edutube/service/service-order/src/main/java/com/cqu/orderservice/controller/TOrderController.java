package com.cqu.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.commonutils.JwtUtils;
import com.cqu.commonutils.R;
import com.cqu.orderservice.entity.TOrder;
import com.cqu.orderservice.service.TOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-15
 */

@Api(description = "订单管理")
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    // 1 生成订单方法
    @ApiOperation(value = "根据课程id生成订单")
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){

        String userId = JwtUtils.getMemberIdByJwtToken(request);

        String orderNo = orderService.createOrder(courseId,userId);

        return R.ok().data("orderId",orderNo);
    }

    // 2 根据订单id生成订单信息
    @ApiOperation(value = "根据订单id查看订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){

        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder one = orderService.getOne(wrapper);

        return R.ok().data("item",one);
    }

    //根据课程id和用户id查询订单表中订单状态
    @ApiOperation(value = "根据课程id和用户id查询订单状态")
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        if(count>0) { //已经支付
            return true;
        } else {
            return false;
        }
    }





}

