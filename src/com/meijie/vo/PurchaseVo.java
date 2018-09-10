package com.meijie.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

public class PurchaseVo {

    private Integer id;
	
	/**
	 * 供应商
	 */
	private String provider;
	
	/**
	 * 采购员
	 */
	private String purchasename;
	
	/**
	 * 共花费的钱
	 */
	private BigDecimal money;
	
	/**
	 * 采购时间
	 */
	private String date;
	
	/**
	 * 备注
	 */
	private String remark;
	

	public PurchaseVo(String provider, String purchasename, BigDecimal money, String date, String remark) {
		super();
		this.provider = provider;
		this.purchasename = purchasename;
		this.money = money;
		this.date = date;
		this.remark = remark;
	}

	public PurchaseVo() {
		super();
	}

	public PurchaseVo(Integer id, String provider, String purchasename, BigDecimal money, String date, String remark) {
		super();
		this.id = id;
		this.provider = provider;
		this.purchasename = purchasename;
		this.money = money;
		this.date = date;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPurchasename() {
		return purchasename;
	}

	public void setPurchasename(String purchasename) {
		this.purchasename = purchasename;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
