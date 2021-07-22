package com.cqu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.cqu.oss.service.OssService;
import com.cqu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author fubibo
 * @create 2021-07-06 上午11:59
 */

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            fileName = uuid+fileName;
            //2 把文件按照日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath+"/"+fileName;
            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName,fileName , inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //把上传之后文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
