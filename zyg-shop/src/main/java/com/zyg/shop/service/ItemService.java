package com.zyg.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.shop.entity.ItemEntity;

import java.util.Map;

/**
 * 商品表
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
public interface ItemService extends IService<ItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

