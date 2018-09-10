package com.meijie.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * 采购表
 * @author Administrator
 *
 */
@Entity
public class Purchase {

	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 供应商
	 */
	@Column(length=50,nullable=true)
	private String provider;
	
	/**
	 * 采购员
	 */
	@Column(length=30,nullable=false)
	private String purchasename;
	
	/**
	 * 共花费的钱
	 */
	@Column(length=50,nullable=false)
	private BigDecimal money;
	
	/**
	 * 采购时间
	 */
	@Column(length=30,nullable=false)
	private String date;
	
	/**
	 * 备注
	 */
	@Column(nullable=true,columnDefinition="TEXT")
	private String remark;
	
	
	/**
	 * 采购详情
	 */
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "purchase")
	private Set<Goods> goods = new HashSet<>();

	public Purchase() {
		super();
	}
	
	

	public Purchase(String provider, String purchasename, BigDecimal money, String date, String remark) {
		super();
		this.provider = provider;
		this.purchasename = purchasename;
		this.money = money;
		this.date = date;
		this.remark = remark;
	}

	public Purchase(Integer id, String provider, String purchasename, BigDecimal money, String date, String remark) {
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
}
