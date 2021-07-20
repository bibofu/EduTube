package com.cqu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author fubibo
 * @create 2021-07-08 下午5:17
 */
public interface VodService {

    String uploadAliVideo(MultipartFile file);

    void removeById(String id);


    void removeMoreAlyVideo(List<String> videoIdList);

    String getAuthById(String id);
}
