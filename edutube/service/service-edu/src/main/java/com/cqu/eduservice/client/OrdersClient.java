package com.cqu.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-15 下午3:04
 */

@Component
@FeignClient(name = "service-order",fallback = OrdersClientFallback.class)
public interface OrdersClient {

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);

    @PostMapping("eduorder/order/findCourse/{memberId}")
    public List<String> findCourse(@PathVariable("memberId") String memberId);
}
