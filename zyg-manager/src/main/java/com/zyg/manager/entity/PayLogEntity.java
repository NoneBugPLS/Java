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
@TableName("tb_pay_log")
public class PayLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 支付订单号
	 */
	@TableId
	private String outTradeNo;
	/**
	 * 创建日期
	 */
	private Date createTime;
	/**
	 * 支付完成时间
	 */
	private Date payTime;
	/**
	 * 支付金额（分）
	 */
	private String totalFee;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 交易号码
	 */
	private String transactionId;
	/**
	 * 交易状态
	 */
	private String tradeState;
	/**
	 * 订单编号列表
	 */
	private String orderList;
	/**
	 * 支付类型
	 */
	private String payType;

}
