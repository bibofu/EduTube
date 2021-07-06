package com.cqu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author fubibo
 * @create 2021-07-06 上午11:59
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
