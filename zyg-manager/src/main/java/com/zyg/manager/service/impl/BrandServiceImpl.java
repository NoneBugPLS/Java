package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.BrandDao;
import com.zyg.manager.entity.BrandEntity;
import com.zyg.manager.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    //1. 分页待条件查询
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1. 构造查询条件
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        //1.2 添加查询条件            //这一步要看看前端数据是以什么对象传过来的，发现是key
        //1.3 得到查询参数
        Object key = params.get("key");
        if(key != null){
            queryWrapper.like("id",key)
                    .or()
                    .like("name",key)
                    .or()
                    .like("first_char",key);
        }
        //1.4 开始带条件查询  把querywrapper封装进来；
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}