package com.zyg.search.service;

import com.zyg.search.entity.ItemVo;

import java.util.Map;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
public interface ItemSearchService {

    Map<String, Object> search(ItemVo vo);
}
