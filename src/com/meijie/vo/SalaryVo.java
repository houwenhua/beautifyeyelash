package com.meijie.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

public class SalaryVo {

	private Integer id;

	private BigDecimal minsalary;

	private BigDecimal reward;

	private BigDecimal summoney;
	
	private String date;
	
	private String watiername;
	
	private Integer waiterid;

	public SalaryVo() {
		super();
	}

	public SalaryVo(Integer id, BigDecimal minsalary, BigDecimal reward, BigDecimal summoney, Integer waiterid) {
		super();
		this.id = id;
		this.minsalary = minsalary;
		this.reward = reward;
		this.summoney = summoney;
		this.waiterid = waiterid;
	}

	public SalaryVo(Integer id, BigDecimal minsalary, BigDecimal reward, BigDecimal summoney, String watiername) {
		super();
		this.id = id;
		this.minsalary = minsalary;
		this.reward = reward;
		this.summoney = summoney;
		this.watiername = watiername;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMinsalary() {
		return minsalary;
	}

	public void setMinsalary(BigDecimal minsalary) {
		this.minsalary = minsalary;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public BigDecimal getSummoney() {
		return summoney;
	}

	public void setSummoney(BigDecimal summoney) {
		this.summoney = summoney;
	}

	public String getWatiername() {
		return watiername;
	}

	public void setWatiername(String watiername) {
		this.watiername = watiername;
	}

	public Integer getWaiterid() {
		return waiterid;
	}

	public void setWaiterid(Integer waiterid) {
		this.waiterid = waiterid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
