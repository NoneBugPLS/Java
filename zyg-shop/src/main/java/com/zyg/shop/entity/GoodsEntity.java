package com.zyg.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Generated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * 
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@Data
@TableName("tb_goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	/**
	 * 商家ID
	 */
	private String sellerId;
	/**
	 * SPU名
	 */
	private String goodsName;
	/**
	 * 默认SKU
	 */
	private String defaultItemId;
	/**
	 * 状态
	 */
	private String auditStatus;
	/**
	 * 是否上架
	 */
	private String isMarketable;
	/**
	 * 品牌
	 */
	private String brandId;
	/**
	 * 副标题
	 */
	private String caption;
	/**
	 * 一级类目
	 */
	private String category1Id;
	/**
	 * 二级类目
	 */
	private String category2Id;
	/**
	 * 三级类目
	 */
	private String category3Id;
	/**
	 * 小图
	 */
	private String smallPic;
	/**
	 * 商城价
	 */
	private BigDecimal price;
	/**
	 * 分类模板ID
	 */
	private String typeTemplateId;
	/**
	 * 是否启用规格
	 */
	private String isEnableSpec;
	/**
	 * 是否删除
	 */
	private String isDelete;

}
