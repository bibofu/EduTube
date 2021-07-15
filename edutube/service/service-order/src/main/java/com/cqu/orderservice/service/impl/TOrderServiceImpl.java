package com.cqu.orderservice.service.impl;

import com.cqu.orderservice.entity.TOrder;
import com.cqu.orderservice.mapper.TOrderMapper;
import com.cqu.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-15
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Override
    public String createOrder(String courseId, String userId) {
        return null;
    }
}
