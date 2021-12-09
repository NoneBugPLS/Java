package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.ProvincesDao;
import com.zyg.manager.entity.ProvincesEntity;
import com.zyg.manager.service.ProvincesService;


@Service("provincesService")
public class ProvincesServiceImpl extends ServiceImpl<ProvincesDao, ProvincesEntity> implements ProvincesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProvincesEntity> page = this.page(
                new Query<ProvincesEntity>().getPage(params),
                new QueryWrapper<ProvincesEntity>()
        );

        return new PageUtils(page);
    }

}