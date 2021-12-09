package com.zyg.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品表
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@Data
@TableName("tb_item")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id，同时也是商品编号
	 */
	@TableId
	private String id;
	/**
	 * 商品标题
	 */
	private String title;
	/**
	 * 商品卖点
	 */
	private String sellPoint;
	/**
	 * 商品价格，单位为：元
	 */
	private BigDecimal price;
	/**
	 * 
	 */
	private Integer stockCount;
	/**
	 * 库存数量
	 */
	private Integer num;
	/**
	 * 商品条形码
	 */
	private String barcode;
	/**
	 * 商品图片
	 */
	private String image;
	/**
	 * 所属类目，叶子类目
	 */
	private String categoryid;
	/**
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String itemSn;
	/**
	 * 
	 */
	private BigDecimal costPirce;
	/**
	 * 
	 */
	private BigDecimal marketPrice;
	/**
	 * 
	 */
	private String isDefault;
	/**
	 * 
	 */
	private String goodsId;
	/**
	 * 
	 */
	private String sellerId;
	/**
	 * 
	 */
	private String cartThumbnail;
	/**
	 * 
	 */
	private String category;
	/**
	 * 
	 */
	private String brand;
	/**
	 * 
	 */
	private String spec;
	/**
	 * 
	 */
	private String seller;

}
