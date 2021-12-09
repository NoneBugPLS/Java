package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.SeckillOrderDao;
import com.zyg.manager.entity.SeckillOrderEntity;
import com.zyg.manager.service.SeckillOrderService;


@Service("seckillOrderService")
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderDao, SeckillOrderEntity> implements SeckillOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillOrderEntity> page = this.page(
                new Query<SeckillOrderEntity>().getPage(params),
                new QueryWrapper<SeckillOrderEntity>()
        );

        return new PageUtils(page);
    }

}