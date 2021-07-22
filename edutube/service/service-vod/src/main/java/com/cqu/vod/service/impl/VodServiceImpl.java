package com.cqu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.cqu.servicebase.exceptionhandler.MyException;
import com.cqu.vod.client.DailyClient;
import com.cqu.vod.service.VodService;
import com.cqu.vod.utils.ConstantVodUtils;
import com.cqu.vod.utils.InitVodClient;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-08 下午5:18
 */

@Service
public class VodServiceImpl implements VodService {

    @Autowired
    private DailyClient dailyClient;


    /**
     * 上传视频到aliyun
     */
    @Override
    public String uploadAliVideo(MultipartFile file) {
        try {

            String filename = file.getOriginalFilename();
            //title：上传之后显示名称
            String title = filename.substring(0, filename.lastIndexOf("."));

            InputStream stream = file.getInputStream();

            UploadStreamRequest request =
                    new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,
                            ConstantVodUtils.ACCESS_KEY_SECRET, title, filename, stream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，
                // 可以返回VideoId同时会返回错误码。
                // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void removeById(String id) {
        try {
            //初始化对象
            DefaultAcsClient client =
                    InitVodClient.initVodClient(
                            ConstantVodUtils.ACCESS_KEY_ID,
                            ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);

        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }

    }


    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {

        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }

    }

    @Override
    public String getAuthById(String id) {
        try {
            DefaultAcsClient client =
                    InitVodClient.initVodClient(
                            ConstantVodUtils.ACCESS_KEY_ID,
                            ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest getVideoPlayAuthRequest =
                    new GetVideoPlayAuthRequest();
            getVideoPlayAuthRequest.setVideoId(id);
            GetVideoPlayAuthResponse response =
                    client.getAcsResponse(getVideoPlayAuthRequest);
            String Auth=response.getPlayAuth();
            dailyClient.updateVideoViewNum();
            return Auth;
        }catch (Exception e){
            e.printStackTrace();
            throw new MyException(20001,"凭证获取失败");
        }
    }


}
