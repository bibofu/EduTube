package com.cqu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.cqu.vod.service.VodService;
import com.cqu.vod.utils.ConstantVodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author fubibo
 * @create 2021-07-08 下午5:18
 */

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {

            String filename = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = filename.substring(0, filename.lastIndexOf("."));

            InputStream stream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET, title, filename, stream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
