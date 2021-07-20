package com.cqu.vod.client;

import com.cqu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author fubibo
 * @create 2021-07-20 下午2:33
 */

@Component
@FeignClient("service-statistics")
public interface DailyClient {

    @GetMapping("/edustatistics/front/updateVideoViewNum")
    public R updateVideoViewNum();
}
