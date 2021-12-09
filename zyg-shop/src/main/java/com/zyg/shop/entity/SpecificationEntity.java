package com.zyg.shop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-26 22:25:10
 */
@Data
@TableName("tb_specification")
public class SpecificationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 名称
	 */
	private String specName;

}
