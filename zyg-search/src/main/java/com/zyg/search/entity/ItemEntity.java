package com.zyg.search.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * 商品表
 * 
 * @author WF
 * @email 2568783935@qq.com
 * @date 2021-07-23 21:10:35
 */
@Data
@TableName("tb_item")
@Document(indexName = "item")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id，同时也是商品编号
	 */
	@TableId
	@Id
	private Long id;
	/**
	 * 商品标题
	 */
	@Field(type = FieldType.Text, analyzer = "ik_smart")
	private String title;
	/**
	 * 商品卖点
	 */
	private String sellPoint;
	/**
	 * 商品价格，单位为：元
	 */
	@Field(type = FieldType.Double)
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
	private Long categoryid;
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
	private Long goodsId;
	/**
	 * 
	 */
	private String sellerId;
	/**
	 * 
	 */
	private String cartThumbnail;
	/**
	 * keyword：不能进行分词
	 */
	@Field(type = FieldType.Keyword)
	private String category;
	/**
	 * 
	 */
	@Field(type = FieldType.Keyword)
	private String brand;
	/**
	 * 
	 */
	private String spec;
	/**
	 * 
	 */
	private String seller;

	//@Transient
	@TableField(exist = false)
	private Map<String,Object> specMap;

}
