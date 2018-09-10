package com.meijie.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

public class SalaryDetailVo {

	private Integer id;
	
	private String waitername;

	/**
	 * 消费日期
	 */
	private String date;

	/**
	 * 消费总金额
	 */
	private BigDecimal allmoney;

	/**
	 * 提成比例
	 */
	private BigDecimal rate;

	/**
	 * 提成金额
	 */
	private BigDecimal reward;

	/**
	 * 状态，用于表示是否是上个月的记录
	 */
	private String state;
	
	

	public SalaryDetailVo() {
		super();
	}

	public SalaryDetailVo(Integer id, String waitername, String date, BigDecimal allmoney, BigDecimal rate,
			BigDecimal reward) {
		super();
		this.id = id;
		this.waitername = waitername;
		this.date = date;
		this.allmoney = allmoney;
		this.rate = rate;
		this.reward = reward;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWaitername() {
		return waitername;
	}

	public void setWaitername(String waitername) {
		this.waitername = waitername;
	}
	
}
