package com.cqu.act.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.act.entity.CrmBanner;
import com.cqu.act.mapper.CrmBannerMapper;
import com.cqu.act.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author CGT
 * @since 2021-07-14
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner>queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 2");
        List<CrmBanner>bannerList=baseMapper.selectList(null);
        return bannerList;
    }
}