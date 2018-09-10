package com.meijie.vo;

import java.math.BigDecimal;

public class GoodsRecordDetailVo {

	private Integer goodsRecordDetailid;
	
	private Integer goodsid;
	
	private String name;
	
	private Integer number;
	
	private BigDecimal price;
	
	private BigDecimal grmoney;
	
	private String grdate;
	
	
	
	public GoodsRecordDetailVo() {
		super();
	}

	

	public GoodsRecordDetailVo(String name, Integer number) {
		super();
		this.name = name;
		this.number = number;
	}



	public GoodsRecordDetailVo(Integer goodsRecordDetailid, Integer goodsid, String name, Integer number,
			BigDecimal price, BigDecimal grmoney, String grdate) {
		super();
		this.goodsRecordDetailid = goodsRecordDetailid;
		this.goodsid = goodsid;
		this.name = name;
		this.number = number;
		this.price = price;
		this.grmoney = grmoney;
		this.grdate = grdate;
	}



	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
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
	

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GoodsRecordDetailVo [goodsid=" + goodsid + ", name=" + name + ", number=" + number + ", grmoney="
				+ grmoney + ", grdate=" + grdate + "]";
	}

	
	
}
