package com.meijie.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class GoodsRecordDetail {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private BigDecimal price;

	@Column(length = 50, nullable = false)
	private Integer number;

	@Column(nullable = false)
	private BigDecimal grmoney;

	@Column(length = 50, nullable = true)
	private String grdate;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "goodsid")
	private Goods goods;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "goodsRecordid")
	private GoodsRecord goodsRecord;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public GoodsRecord getGoodsRecord() {
		return goodsRecord;
	}

	public void setGoodsRecord(GoodsRecord goodsRecord) {
		this.goodsRecord = goodsRecord;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
