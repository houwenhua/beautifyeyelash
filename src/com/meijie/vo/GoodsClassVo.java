package com.meijie.vo;

import javax.persistence.Column;

public class GoodsClassVo {

	private Integer id;

	private String name;

	private String brand;

	private Long number;

	public GoodsClassVo() {
		super();
	}

	public GoodsClassVo(Integer id, String name, String brand, Long number) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.number = number;
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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
