package com.cqu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author fubibo
 * @create 2021-07-08 下午5:17
 */
public interface VodService {

    String uploadAliVideo(MultipartFile file);


}
