package com.cqu.edustatistics.utils;

import com.cqu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author CGT
 * @create 2021-07-20 15:27
 */
@Component
public class UcenterClientFallback implements UcenterClient{

    @Override
    public R registerCount(String day) {
        return null;
    }

    @Override
    public R loginCount(String day) {
        return null;
    }

    @Override
    public R videoCount(String day) {
        return null;
    }

    @Override
    public R courseCount(String day) {
        return null;
    }
}
