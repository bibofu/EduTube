package com.cqu.eduservice.client;

import com.cqu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-12 上午11:31
 */

@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String id) {
        return R.error().message("time out");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
