package com.meijie.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Material {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50,nullable = false)
	private String name;
	
	@Column(length=50,nullable = false)
	private String brand;
	
	@Column(length=50,nullable = true)
	private String productiondate = "暂无";
	
	@Column(length=50,nullable = false)
	private String producer;
	
	@Column(nullable = true,columnDefinition="TEXT")
	private String remark;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="serviceid",nullable=true)
	private Service service;

	public Material() {
		super();
	}

	public Material(String name, String brand, String productiondate, String producer, String remark, Service service) {
		super();
		this.name = name;
		this.brand = brand;
		this.productiondate = productiondate;
		this.producer = producer;
		this.remark = remark;
		this.service = service;
	}

	public Material(Integer id, String name, String brand, String productiondate, String producer, String remark,
			Service service) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.productiondate = productiondate;
		this.producer = producer;
		this.remark = remark;
		this.service = service;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
}
