package com.meijie.vo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.meijie.entity.Goods;

public class GoodsRecordVo {

	private Integer id;
	
	private BigDecimal grmoney;
	
	private String grdate;
	
	private String name;
	
	private BigDecimal reallmoney;
	
	/**
	 * 服务人
	 * */
	private String waitername;
	
	private Integer waiterid;
	
	private String startdate;
	
	private String enddate;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public BigDecimal getReallmoney() {
		return reallmoney;
	}

	public void setReallmoney(BigDecimal reallmoney) {
		this.reallmoney = reallmoney;
	}

	public String getWaitername() {
		return waitername;
	}

	public void setWaitername(String waitername) {
		this.waitername = waitername;
	}

	public Integer getWaiterid() {
		return waiterid;
	}

	public void setWaiterid(Integer waiterid) {
		this.waiterid = waiterid;
	}

	public GoodsRecordVo(Integer id, BigDecimal grmoney, String grdate, String name,BigDecimal reallmoney,String waitername) {
		super();
		this.id = id;
		this.grmoney = grmoney;
		this.grdate = grdate;
		this.name = name;
		this.reallmoney = reallmoney;
		this.waitername = waitername;
	}

	public GoodsRecordVo() {
		super();
	}
	
	
	
}
