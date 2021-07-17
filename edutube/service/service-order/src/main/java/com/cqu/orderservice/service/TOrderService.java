package com.cqu.orderservice.service;

import com.cqu.orderservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-16
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, String userId);
}
