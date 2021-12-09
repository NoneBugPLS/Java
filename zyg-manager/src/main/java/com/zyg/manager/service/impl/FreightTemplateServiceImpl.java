package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.FreightTemplateDao;
import com.zyg.manager.entity.FreightTemplateEntity;
import com.zyg.manager.service.FreightTemplateService;


@Service("freightTemplateService")
public class FreightTemplateServiceImpl extends ServiceImpl<FreightTemplateDao, FreightTemplateEntity> implements FreightTemplateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FreightTemplateEntity> page = this.page(
                new Query<FreightTemplateEntity>().getPage(params),
                new QueryWrapper<FreightTemplateEntity>()
        );

        return new PageUtils(page);
    }

}