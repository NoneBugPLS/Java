package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 内容分类
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_content_category")
public class ContentCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类目ID
	 */
	@TableId
	private String id;
	/**
	 * 分类名称
	 */
	private String name;

}
