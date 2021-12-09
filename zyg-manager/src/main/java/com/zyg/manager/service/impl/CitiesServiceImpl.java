package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.CitiesDao;
import com.zyg.manager.entity.CitiesEntity;
import com.zyg.manager.service.CitiesService;


@Service("citiesService")
public class CitiesServiceImpl extends ServiceImpl<CitiesDao, CitiesEntity> implements CitiesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CitiesEntity> page = this.page(
                new Query<CitiesEntity>().getPage(params),
                new QueryWrapper<CitiesEntity>()
        );

        return new PageUtils(page);
    }

}