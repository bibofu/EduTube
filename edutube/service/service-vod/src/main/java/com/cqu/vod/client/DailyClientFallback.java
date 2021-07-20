package com.cqu.vod.client;

import com.cqu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author CGT
 * @create 2021-07-20 15:31
 */
@Component
public class DailyClientFallback implements DailyClient{
    @Override
    public R updateVideoViewNum() {
        return null;
    }
}
