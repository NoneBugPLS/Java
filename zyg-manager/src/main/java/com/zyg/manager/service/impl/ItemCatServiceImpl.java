package com.zyg.manager.service.impl;

import org.junit.jupiter.params.aggregator.AggregateWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.ItemCatDao;
import com.zyg.manager.entity.ItemCatEntity;
import com.zyg.manager.service.ItemCatService;


@Service("itemCatService")
public class ItemCatServiceImpl extends ServiceImpl<ItemCatDao, ItemCatEntity> implements ItemCatService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ItemCatEntity> page = this.page(
                new Query<ItemCatEntity>().getPage(params),
                new QueryWrapper<ItemCatEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ItemCatEntity> findAll() {
        //1. 查询所有itemcat
        List<ItemCatEntity> list = this.list();
        //2. 把他们放到redis中
        for (ItemCatEntity entity : list) {
            redisTemplate.boundHashOps("itemCats").put(entity.getName(),entity.getTypeId());
        }
        return list;
    }

}