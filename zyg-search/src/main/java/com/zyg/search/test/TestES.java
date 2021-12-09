package com.zyg.search.test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyg.search.dao.ItemDao;
import com.zyg.search.entity.ItemEntity;
import com.zyg.search.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;
import java.util.Map;

/**
 * ------------------------------
 * 功能：
 * 作者：WF
 * 微信：hbxfwf13590332912
 * 创建时间：2021/12/3-9:55
 * ------------------------------
 */
@SpringBootTest
public class TestES {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Test
    public void test01(){
        //1. 查询所有的sku列表
        List<ItemEntity> itemEntities = itemService.list(new QueryWrapper<ItemEntity>().eq("status", 1));
        //2. 遍历集合为动态域集合赋值
        for (ItemEntity item : itemEntities) {
            //2.1 得到这个sku商品的规格对象的字符串
            String spec = item.getSpec();
            //2.2 转换为java的Map
            Map map = JSON.parseObject(spec, Map.class);
            //2.3 直接设置关联的动态域集合
            item.setSpecMap(map);
        }
        //3. 保存到索引库中
        restTemplate.save(itemEntities);
        System.out.println("保存到索引库成功！");

    }
}
