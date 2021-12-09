package com.zyg.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.shop.entity.SpecificationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
public interface SpecificationService extends IService<SpecificationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map> findByTypeId(String typeId);
}

