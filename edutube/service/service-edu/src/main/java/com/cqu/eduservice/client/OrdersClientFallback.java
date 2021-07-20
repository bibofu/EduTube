package com.cqu.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author fubibo
 * @create 2021-07-20 下午3:19
 */

@Component
public class OrdersClientFallback implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
