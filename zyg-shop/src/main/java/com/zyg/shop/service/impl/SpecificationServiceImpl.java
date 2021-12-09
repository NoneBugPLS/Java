package com.zyg.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.shop.entity.SpecificationOptionEntity;
import com.zyg.shop.entity.TypeTemplateEntity;
import com.zyg.shop.service.SpecificationOptionService;
import com.zyg.shop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.SpecificationDao;
import com.zyg.shop.entity.SpecificationEntity;
import com.zyg.shop.service.SpecificationService;


@Service("specificationService")
public class SpecificationServiceImpl extends ServiceImpl<SpecificationDao, SpecificationEntity> implements SpecificationService {

    @Autowired
    private TypeTemplateService typeTemplateService;
    @Autowired
    private SpecificationOptionService OptionService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpecificationEntity> page = this.page(
                new Query<SpecificationEntity>().getPage(params),
                new QueryWrapper<SpecificationEntity>()
        );

        return new PageUtils(page);
    }

    //通过template中的typeId找到规格信息
    @Override
    public List<Map> findByTypeId(String typeId) {
        //1. 通过Id查出template对象
        TypeTemplateEntity templateEntity = typeTemplateService.getById(typeId);
        //2. 得到template中的规格信息
        String specIds = templateEntity.getSpecIds();
        //3. 把得到的规格信息对象放入lits中
        List<Map> spec = JSON.parseArray(specIds,Map.class);
        //4. 遍历集合，为每个spec添加options
        for (Map map : spec) {
            //4.1 找到规格id
            Integer id = (Integer) map.get("id");
            //4.2 通过id找到options
            List<SpecificationOptionEntity> optionEntities = OptionService.list(new QueryWrapper<SpecificationOptionEntity>().eq("spec_id", id));
            map.put("options",optionEntities);
        }
        //4. 返回spec
        return spec;
    }

}