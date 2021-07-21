package com.cqu.orderservice.client;

import com.cqu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.stereotype.Component;

/**
 * @author fubibo
 * @create 2021-07-20 下午3:33
 */

@Component
public class UcenterClientfallback implements UcenterClient{
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        return null;
    }
}
