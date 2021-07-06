package com.cqu.oss.controller;

import com.cqu.commonutils.R;
import com.cqu.oss.service.OssService;
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
 * @create 2021-07-06 下午12:00
 */

@Api(description = "aliyun上传头像")
@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "上传方法")
    @PostMapping
    public R upload(MultipartFile file){
        String s = ossService.uploadFileAvatar(file);

        return R.ok().data("url",s);



    }


}
