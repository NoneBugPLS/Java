package com.zyg.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.search.entity.ItemEntity;


import java.util.Map;

/**
 * 商品表
 *
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-26 09:39:42
 */
public interface ItemService extends IService<ItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

