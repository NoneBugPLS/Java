package com.zyg.manager.dao;

import com.zyg.manager.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
