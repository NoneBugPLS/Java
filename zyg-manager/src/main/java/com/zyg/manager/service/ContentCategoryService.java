package com.zyg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.manager.entity.ContentCategoryEntity;

import java.util.Map;

/**
 * 内容分类
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
public interface ContentCategoryService extends IService<ContentCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

