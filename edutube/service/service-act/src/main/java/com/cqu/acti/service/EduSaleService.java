package com.cqu.acti.service;

import com.cqu.acti.entity.EduSale;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 打折表 服务类
 * </p>
 *
 * @author CGT
 * @since 2021-07-17
 */
public interface EduSaleService extends IService<EduSale> {
    void removeByActId(String id);

}
