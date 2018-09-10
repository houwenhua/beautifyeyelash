package com.meijie.vo;

import javax.persistence.Column;

public class MaterialVo {

	private Integer id;

	private String name;

	private String brand;

	private String productiondate = "暂无";

	private String producer;

	private String remark;
	
	private Integer serviceid;

	public MaterialVo() {
		super();
	}
	
	public MaterialVo(Integer id, String name, String brand, String productiondate, String producer, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.productiondate = productiondate;
		this.producer = producer;
		this.remark = remark;
	}

	public MaterialVo(Integer id, String name, String brand, String productiondate, String producer, String remark,Integer serviceid) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.productiondate = productiondate;
		this.producer = producer;
		this.remark = remark;
		this.serviceid = serviceid;
	}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductiondate() {
		return productiondate;
	}

	public void setProductiondate(String productiondate) {
		this.productiondate = productiondate;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}
	
	

}
