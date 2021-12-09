package com.zyg.search.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 功能: 代表封装的前端查询参数
 * 参数:
 * 返回值:
 * 时间: 2021/11/2 15:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo implements Serializable {
    //1. 查询关键字对象
    private String keywords;
    //2. 商品分类
    private String category;
    //3. 品牌查询
    private String brand;
    //4. 规格查询(类似：[网络：4G,机身内存：128G])
    private List<String> spec;
    //5. 价格区间（price:500_1000）
    private String price;
    //6. 排序(类似：asc:price)
    private String sort;
    //7. 当前页
    private int page = 1;
    //8. 每页大小
    private int pageSize = 10;
}