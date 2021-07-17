package com.cqu.acti.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.acti.entity.EduSale;
import com.cqu.acti.mapper.EduSaleMapper;
import com.cqu.acti.service.EduSaleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 打折表 服务实现类
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
@Service
public class EduSaleServiceImpl extends ServiceImpl<EduSaleMapper, EduSale> implements EduSaleService {
    @Override
    public void removeByActId(String id) {
        QueryWrapper<EduSale> wrapper=new QueryWrapper<>();
        wrapper.eq("act",id);
        baseMapper.delete(wrapper);
    }
}
