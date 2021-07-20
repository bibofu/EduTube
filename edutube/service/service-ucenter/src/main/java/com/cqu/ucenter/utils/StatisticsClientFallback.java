package com.cqu.ucenter.utils;

import com.cqu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author CGT
 * @create 2021-07-20 15:30
 */
@Component
public class StatisticsClientFallback implements StatisticsClient{

    @Override
    public R updateLoginNum(String day) {
        return null;
    }

    @Override
    public R updateRegisterNum(String day) {
        return null;
    }
}
