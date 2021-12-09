package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 商品id
	 */
	private String itemId;
	/**
	 * SPU_ID
	 */
	private String goodsId;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 商品标题
	 */
	private String title;
	/**
	 * 商品单价
	 */
	private BigDecimal price;
	/**
	 * 商品购买数量
	 */
	private Integer num;
	/**
	 * 商品总金额
	 */
	private BigDecimal totalFee;
	/**
	 * 商品图片地址
	 */
	private String picPath;
	/**
	 * 
	 */
	private String sellerId;

}
