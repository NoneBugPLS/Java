package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.AreasDao;
import com.zyg.manager.entity.AreasEntity;
import com.zyg.manager.service.AreasService;


@Service("areasService")
public class AreasServiceImpl extends ServiceImpl<AreasDao, AreasEntity> implements AreasService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AreasEntity> page = this.page(
                new Query<AreasEntity>().getPage(params),
                new QueryWrapper<AreasEntity>()
        );

        return new PageUtils(page);
    }

}