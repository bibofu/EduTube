package com.cqu.acti.service;

import com.cqu.acti.entity.EduActivity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 活动 服务类
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
public interface EduActivityService extends IService<EduActivity> {
    String saveActivity(EduActivity activity);
}
