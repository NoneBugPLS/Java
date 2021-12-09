package com.zyg.shop.entity.group;

import com.zyg.shop.entity.GoodsDescEntity;
import com.zyg.shop.entity.GoodsEntity;
import com.zyg.shop.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private GoodsEntity goods;              //spu
    private GoodsDescEntity goodsDesc;      //spu商品描述
    private List<ItemEntity> items;         //sku商品列表
}