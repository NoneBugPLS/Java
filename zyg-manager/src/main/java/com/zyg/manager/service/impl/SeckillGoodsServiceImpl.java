package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.SeckillGoodsDao;
import com.zyg.manager.entity.SeckillGoodsEntity;
import com.zyg.manager.service.SeckillGoodsService;


@Service("seckillGoodsService")
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsDao, SeckillGoodsEntity> implements SeckillGoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeckillGoodsEntity> page = this.page(
                new Query<SeckillGoodsEntity>().getPage(params),
                new QueryWrapper<SeckillGoodsEntity>()
        );

        return new PageUtils(page);
    }

}