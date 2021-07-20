package com.cqu.eduservice.client;

import com.cqu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author fubibo
 * @create 2021-07-20 下午3:21
 */

@Component
public class StatisticsClientFallback implements StatisticsClient{
    @Override
    public R updateCourseNum() {
        return null;
    }
}
