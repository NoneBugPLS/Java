package com.zyg.manager.entity;

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
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_content")
public class ContentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 内容类目ID
	 */
	private String categoryId;
	/**
	 * 内容标题
	 */
	private String title;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 图片绝对路径
	 */
	private String pic;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 排序
	 */
	private Integer sortOrder;

}
