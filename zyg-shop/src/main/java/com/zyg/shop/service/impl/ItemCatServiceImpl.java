package com.zyg.shop.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.ItemCatDao;
import com.zyg.shop.entity.ItemCatEntity;
import com.zyg.shop.service.ItemCatService;


@Service("itemCatService")
public class ItemCatServiceImpl extends ServiceImpl<ItemCatDao, ItemCatEntity> implements ItemCatService {

    //1. 查询分类并分页
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemCatEntity> page = this.page(
                new Query<ItemCatEntity>().getPage(params),
                new QueryWrapper<ItemCatEntity>()
        );

        return new PageUtils(page);
    }

    //2. 根据parentsId查询子分类列表
    @Override
    public List<ItemCatEntity> findByParentId(String itemCatId) {
        return this.list(new QueryWrapper<ItemCatEntity>().eq("parent_id",itemCatId));
    }

}