package com.zyg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.manager.entity.ItemCatEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品类目
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
public interface ItemCatService extends IService<ItemCatEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<ItemCatEntity> findAll();
}

