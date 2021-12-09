package com.zyg.shop.dao;

import com.zyg.shop.entity.ItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@Mapper
public interface ItemDao extends BaseMapper<ItemEntity> {
	
}
