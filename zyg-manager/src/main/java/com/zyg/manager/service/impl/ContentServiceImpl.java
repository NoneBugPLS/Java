package com.zyg.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.manager.dao.ContentDao;
import com.zyg.manager.entity.ContentEntity;
import com.zyg.manager.service.ContentService;


@Service("contentService")
public class ContentServiceImpl extends ServiceImpl<ContentDao, ContentEntity> implements ContentService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContentEntity> page = this.page(
                new Query<ContentEntity>().getPage(params),
                new QueryWrapper<ContentEntity>()
        );

        return new PageUtils(page);
    }


    //2. 从缓存中得到图片，第一次没有就从数据库调取
    @Override
    public List<ContentEntity> findAll() {
        List<ContentEntity> contentEntities = null;
        //1. 从redis中得到广告列表
        //1.1 得到广告列表的字符串
        String contentListStr = redisTemplate.opsForValue().get("contentList");
        //1.2 转换成对象
        if (StrUtil.isNotBlank(contentListStr)) { //如果有内容就转换
            contentEntities = JSON.parseArray(contentListStr, ContentEntity.class);
        } else {      //1.3 没有内容就从数据库中取
            List<ContentEntity> list = this.list();
            System.out.println("从数据库中取广告列表。。。");
            //2.5 放到redis中
            redisTemplate.opsForValue().set("contentList", JSON.toJSONString(list));
        }

        //1.4 返回广告内容
        return contentEntities;
    }

}