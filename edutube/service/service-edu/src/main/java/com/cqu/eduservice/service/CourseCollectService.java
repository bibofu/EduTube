package com.cqu.eduservice.service;

import com.cqu.eduservice.entity.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fubibo
 * @since 2021-07-20
 */
public interface CourseCollectService extends IService<CourseCollect> {

    void collect(String memberId, String courseId);
}
