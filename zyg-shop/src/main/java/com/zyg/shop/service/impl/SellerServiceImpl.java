package com.zyg.shop.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.SellerDao;
import com.zyg.shop.entity.SellerEntity;
import com.zyg.shop.service.SellerService;


@Service("sellerService")
public class SellerServiceImpl extends ServiceImpl<SellerDao, SellerEntity> implements SellerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SellerEntity> page = this.page(
                new Query<SellerEntity>().getPage(params),
                new QueryWrapper<SellerEntity>()
        );

        return new PageUtils(page);
    }

}