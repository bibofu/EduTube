package com.cqu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.cqu.commonutils.R;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.vod.service.VodService;
import com.cqu.vod.utils.ConstantVodUtils;
import com.cqu.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-08 下午5:15
 */

@Api(description = "阿里云视频点播管理")
@RestController
@RequestMapping("eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;






    //1. 上传视频到阿里云
    @ApiOperation(value = "上传视频方法")
    @PostMapping("uploadVideo")
    public R upload(MultipartFile file){

        String id = vodService.uploadAliVideo(file);
        return R.ok().data("videoId",id);

    }

    //2. 根据视频id删除阿里云视频
    @ApiOperation(value = "根据id删除视频")
    @DeleteMapping("deleteAliVideo/{id}")
    public R deleteVideo(@PathVariable String id){

        vodService.removeById(id);
        return R.ok();

    }

    //3. 删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @ApiOperation(value = "删除多个视频")
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }

}
