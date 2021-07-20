package com.cqu.orderservice.client;

import com.cqu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.stereotype.Component;

/**
 * @author fubibo
 * @create 2021-07-20 下午3:32
 */

@Component
public class EduClientfallback implements EduClient{
    @Override
    public CourseWebVoOrder getCourseInfoOrder(String id) {
        return null;
    }
}
