package com.zyg.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;
import com.zyg.search.dao.ItemDao;
import com.zyg.search.entity.ItemEntity;
import com.zyg.search.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("itemService")
public class ItemServiceImpl extends ServiceImpl<ItemDao, ItemEntity> implements ItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemEntity> page = this.page(
                new Query<ItemEntity>().getPage(params),
                new QueryWrapper<ItemEntity>()
        );

        return new PageUtils(page);
    }

}