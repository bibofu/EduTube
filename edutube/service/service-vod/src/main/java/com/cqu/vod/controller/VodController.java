package com.cqu.vod.controller;

import com.cqu.commonutils.R;
import com.cqu.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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


    @ApiOperation(value = "上传视频方法")
    @PostMapping("uploadVideo")
    public R upload(MultipartFile file){

        String id = vodService.uploadAliVideo(file);


        return R.ok().data("videoId",id);

    }

}
