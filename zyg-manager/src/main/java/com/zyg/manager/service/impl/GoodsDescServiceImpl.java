package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.GoodsDescDao;
import com.zyg.manager.entity.GoodsDescEntity;
import com.zyg.manager.service.GoodsDescService;


@Service("goodsDescService")
public class GoodsDescServiceImpl extends ServiceImpl<GoodsDescDao, GoodsDescEntity> implements GoodsDescService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsDescEntity> page = this.page(
                new Query<GoodsDescEntity>().getPage(params),
                new QueryWrapper<GoodsDescEntity>()
        );

        return new PageUtils(page);
    }

}