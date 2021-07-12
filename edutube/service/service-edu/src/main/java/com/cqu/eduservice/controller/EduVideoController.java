package com.cqu.eduservice.controller;


import com.cqu.commonutils.R;
import com.cqu.eduservice.client.VodClient;
import com.cqu.eduservice.entity.EduVideo;
import com.cqu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author fubibo
 * @since 2021-07-07
 */

@Api(description = "小节管理")
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    private VodClient vodClient;




    //1. 增加小节
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo video){
        videoService.save(video);
        return R.ok();
    }

    //2. 删除小节
    //todo
    @ApiOperation(value = "根据小节id删除视频")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){

        //根据小节id获取视频id
        String videoSourceId = videoService.getById(id).getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {

            vodClient.removeVideo(videoSourceId);

        }
        videoService.removeById(id);

        return R.ok();
    }

    //修改小节
    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo video){
        videoService.updateById(video);

        return R.ok();
    }



}

