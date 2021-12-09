package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 行政区域县区信息表
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_areas")
public class AreasEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一ID
	 */
	@TableId
	private Integer id;
	/**
	 * 区域ID
	 */
	private String areaid;
	/**
	 * 区域名称
	 */
	private String area;
	/**
	 * 城市ID
	 */
	private String cityid;

}
