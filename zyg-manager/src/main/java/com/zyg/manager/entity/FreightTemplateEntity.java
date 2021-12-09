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
@TableName("tb_freight_template")
public class FreightTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 商家ID
	 */
	private String sellerId;
	/**
	 * 是否默认   （‘Y’是   'N'否）
	 */
	private String isDefault;
	/**
	 * 模版名称
	 */
	private String name;
	/**
	 * 发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
	 */
	private String sendTimeType;
	/**
	 * 统一价格
	 */
	private BigDecimal price;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
