package com.zyg.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.zyg.shop.entity.GoodsDescEntity;
import com.zyg.shop.entity.ItemEntity;
import com.zyg.shop.entity.group.Goods;
import com.zyg.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyg.common.utils.PageUtils;
import com.zyg.common.utils.Query;

import com.zyg.shop.dao.GoodsDao;
import com.zyg.shop.entity.GoodsEntity;
import org.springframework.transaction.annotation.Transactional;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Autowired
    private GoodsDescService goodsDescService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ItemCatService itemCatService;
    @Autowired
    private SellerService sellerService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>().getPage(params),
                new QueryWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }

    //保存商品
    @Override
    @Transactional
    public void save(Goods goods) {
        //1. 保存GoodsEntity
        goods.getGoods().setAuditStatus("0");   //0:未审核 1：审核通过
        goods.getGoods().setSellerId("baidu");  //商家id
        this.save(goods.getGoods());
        //2. 保存goodsDescEntity
        //2.1 先设置ID
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        //2.2 保存
        goodsDescService.save(goods.getGoodsDesc());

        //3. 添加sku商品列表
        saveItem(goods);
    }

    //通过ID查询商品对象
    @Override
    public Goods findById(String id) {
        //1. 通过ID查询goods， 跟goodsdesc  因为两个数据的id 是用的同一个所以OK.
        GoodsEntity goodsEntity = this.getById(id);
        GoodsDescEntity goodsDescEntity = goodsDescService.getById(id);
        //2. 通过外键查询items   因为items表中 goods id 是一个外键
        List<ItemEntity> itemEntities = itemService.list(new QueryWrapper<ItemEntity>().eq("goods_id",id));
        //3. 构造组合对象
        Goods goods = new Goods();
        goods.setGoods(goodsEntity);
        goods.setGoodsDesc(goodsDescEntity);
        goods.setItems(itemEntities);

        return goods;
    }




    private void saveItem(Goods goods) {
        System.out.println("goods = " + goods);
        List<ItemEntity> items = goods.getItems();
        for (ItemEntity item : items) {
            //1.3.1 设置商品id
            item.setGoodsId(goods.getGoods().getId());
            item.setCreateTime(new Date());
            item.setCategoryid(goods.getGoods().getCategory3Id());
            //1.3.2 设置分类
            item.setCategory(itemCatService.getById(goods.getGoods().getCategory3Id()).getName());
            //1.3.2 设置品牌名称
            item.setBrand(brandService.getById(goods.getGoods().getBrandId()).getName());

            //1.3.3 设置图像
            String itemImages = goods.getGoodsDesc().getItemImages();
            List<Map> maps = JSON.parseArray(itemImages, Map.class);
            if(maps != null && maps.size() > 0) {
                item.setImage(maps.get(0).get("url") + "");
            }
            //1.3.4 设置商家id
            item.setSellerId(goods.getGoods().getSellerId());
            //1.3.5 设置商家名称
            String nickName = sellerService.getById(goods.getGoods().getSellerId()).getNickName();
            item.setSeller(nickName);
            //1.3.6 设置商家标题名称
            item.setTitle(goods.getGoods().getGoodsName());
            //1.3.7 设置商品状态值
            item.setStatus("1");
            //1.3.8 设置修改时间
            item.setUpdateTime(new Date());
            //1.3.9 添加item
            itemService.save(item);
        }
    }

    //修改商品
    @Override
    public void update(Goods goods) {
        //1. 修改goods
        this.updateById(goods.getGoods());
        //2. 修改goodsdesc
        //2.1 创建goods 与 goodsdesc 外键关系
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        goodsDescService.updateById(goods.getGoodsDesc());
        //3. 修改 item这种 一对多的数据  我们就删除再创建
        //3.1 删除
        itemService.remove(new QueryWrapper<ItemEntity>().eq("goods_id",goods.getGoods().getId()));
        //3.2再重新赋值
        saveItem(goods);

    }



}