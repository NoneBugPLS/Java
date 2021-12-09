package com.zyg.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.SpecificationOptionDao;
import com.zyg.shop.entity.SpecificationOptionEntity;
import com.zyg.shop.service.SpecificationOptionService;


@Service("specificationOptionService")
public class SpecificationOptionServiceImpl extends ServiceImpl<SpecificationOptionDao, SpecificationOptionEntity> implements SpecificationOptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpecificationOptionEntity> page = this.page(
                new Query<SpecificationOptionEntity>().getPage(params),
                new QueryWrapper<SpecificationOptionEntity>()
        );

        return new PageUtils(page);
    }

}