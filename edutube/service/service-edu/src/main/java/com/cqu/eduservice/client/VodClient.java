package com.cqu.eduservice.client;

import com.cqu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-12 上午10:36
 */

/**
 * 远程调用client，这里调用service-vod中视频服务
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @DeleteMapping(value = "/eduvod/video/deleteAliVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    //定义调用删除多个视频的方法
    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
