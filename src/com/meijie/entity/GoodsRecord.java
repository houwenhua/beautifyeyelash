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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GoodsRecord {

	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 选择的商品
	 */
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "goodsRecord")
	private Set<GoodsRecordDetail> goodsRecordDetail = new HashSet<>();
	
	/**
	 * 消费的金额
	 */
	@Column(nullable = false)
	private BigDecimal grmoney;
	
	/**
	 * 消费日期
	 */
	@Column(nullable = false, length = 30)
	private String grdate;
	
	/**
	 * 实际收费
	 */
	@Column(nullable = false, length = 30)
	private BigDecimal reallmoney;
	
	/**
	 * 服务人
	 * */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="waiterid")
	private Waiter waiter;
	
	

	public GoodsRecord() {
		super();
	}

	public GoodsRecord(BigDecimal grmoney, String grdate) {
		super();
		this.grmoney = grmoney;
		this.grdate = grdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getGrmoney() {
		return grmoney;
	}

	public void setGrmoney(BigDecimal grmoney) {
		this.grmoney = grmoney;
	}

	public String getGrdate() {
		return grdate;
	}

	public void setGrdate(String grdate) {
		this.grdate = grdate;
	}

	public Set<GoodsRecordDetail> getGoodsRecordDetail() {
		return goodsRecordDetail;
	}

	public void setGoodsRecordDetail(Set<GoodsRecordDetail> goodsRecordDetail) {
		this.goodsRecordDetail = goodsRecordDetail;
	}

	public BigDecimal getReallmoney() {
		return reallmoney;
	}

	public void setReallmoney(BigDecimal reallmoney) {
		this.reallmoney = reallmoney;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

}
