package com.cqu.orderservice.client;

import com.cqu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author fubibo
 * @create 2021-07-16 下午2:57
 */

@Component
@FeignClient(name = "service-edu",fallback = EduClientfallback.class)
public interface EduClient {

    //根据课程id查询课程信息
    @PostMapping("/eduservice/courseFront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);




}
