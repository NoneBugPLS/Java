package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 行政区域地州市信息表
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_cities")
public class CitiesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 唯一ID
	 */
	@TableId
	private Integer id;
	/**
	 * 城市ID
	 */
	private String cityid;
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 省份ID
	 */
	private String provinceid;

}
