package com.zyg.manager.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.PayLogDao;
import com.zyg.manager.entity.PayLogEntity;
import com.zyg.manager.service.PayLogService;


@Service("payLogService")
public class PayLogServiceImpl extends ServiceImpl<PayLogDao, PayLogEntity> implements PayLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PayLogEntity> page = this.page(
                new Query<PayLogEntity>().getPage(params),
                new QueryWrapper<PayLogEntity>()
        );

        return new PageUtils(page);
    }

}