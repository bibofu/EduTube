package com.cqu.acti.service.impl;

import com.cqu.acti.entity.EduActivity;
import com.cqu.acti.mapper.EduActivityMapper;
import com.cqu.acti.service.EduActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.servicebase.exceptionhandler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
@Service
public class EduActivityServiceImpl extends ServiceImpl<EduActivityMapper, EduActivity> implements EduActivityService {
    @Override
    public String saveActivity(EduActivity activity) {
        EduActivity eduActivity=new EduActivity();
        BeanUtils.copyProperties(activity,eduActivity);
        int insert=baseMapper.insert(eduActivity);
        if(insert==0)
        {
            throw new MyException(20001,"添加活动失败");
        }
        String id=eduActivity.getId();
        return id;
    }
}
