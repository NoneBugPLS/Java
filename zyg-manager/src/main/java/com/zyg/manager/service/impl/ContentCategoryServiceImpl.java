package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.ContentCategoryDao;
import com.zyg.manager.entity.ContentCategoryEntity;
import com.zyg.manager.service.ContentCategoryService;


@Service("contentCategoryService")
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryDao, ContentCategoryEntity> implements ContentCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContentCategoryEntity> page = this.page(
                new Query<ContentCategoryEntity>().getPage(params),
                new QueryWrapper<ContentCategoryEntity>()
        );

        return new PageUtils(page);
    }

}