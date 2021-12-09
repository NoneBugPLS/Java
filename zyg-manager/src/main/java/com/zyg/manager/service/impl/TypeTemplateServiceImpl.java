package com.zyg.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.manager.entity.SpecificationOptionEntity;
import com.zyg.manager.service.SpecificationOptionService;
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

import com.zyg.manager.dao.TypeTemplateDao;
import com.zyg.manager.entity.TypeTemplateEntity;
import com.zyg.manager.service.TypeTemplateService;


@Service("typeTemplateService")
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateDao, TypeTemplateEntity> implements TypeTemplateService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private SpecificationOptionService optionService;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TypeTemplateEntity> page = this.page(
                new Query<TypeTemplateEntity>().getPage(params),
                new QueryWrapper<TypeTemplateEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<TypeTemplateEntity> findAll() {
        //列表所有模板：
        List<TypeTemplateEntity> list = this.list();
        //遍历通过模板id 的到品牌列表 与 规格列表
        for (TypeTemplateEntity entity : list) {
            //一、 存放品牌列表
            //1. 得到模板id
            String id = entity.getId();
            //2. 找到品牌列表
            String brandIds = entity.getBrandIds();
            //3. 把 id 当做 key  品牌列表当做 value 放到redis中
            redisTemplate.boundHashOps("brandList").put(id,brandIds);

            //二、 存放规格列表
            //1. 得到规格列表
            String specIds = entity.getSpecIds();
            //因为规格列表里面是个数组对象，有 两个值 “id”  “text” ，所以要遍历拿值
            //2. 把规格列表转换为Map
            List<Map> maps = JSON.parseArray(specIds, Map.class);
            //3. 遍历拿值
            for (Map map : maps) {
                //3.1 拿到 “id”  这个id 就对应 specification中的id
                Object specId = map.get("id");
                //3.2 通过id 在 specification 中拿到它所对应的规格
                List<SpecificationOptionEntity> options = optionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", specId));
                //3.3 放入map中
                map.put("options",options);
            }
            //4. 转换为json串并放到redis中
            redisTemplate.boundHashOps("specList").put(id,JSON.toJSONString(maps));
        }
        return list;
    }

}