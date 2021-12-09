package com.zyg.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.TypeTemplateDao;
import com.zyg.shop.entity.TypeTemplateEntity;
import com.zyg.shop.service.TypeTemplateService;


@Service("typeTemplateService")
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateDao, TypeTemplateEntity> implements TypeTemplateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TypeTemplateEntity> page = this.page(
                new Query<TypeTemplateEntity>().getPage(params),
                new QueryWrapper<TypeTemplateEntity>()
        );

        return new PageUtils(page);
    }

}