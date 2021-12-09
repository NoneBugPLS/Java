package com.zyg.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyg.search.entity.ItemEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * 
 * @author hbxfwf
 * @email 2568783935@qq.com
 * @date 2021-11-26 09:39:42
 */
@Mapper
public interface ItemDao extends BaseMapper<ItemEntity> {
	
}
