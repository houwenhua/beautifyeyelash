package com.meijie.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GoodsClass {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 商品名
	 */
	@Column(length=50,nullable = false)
	private String name;
	

	@Column(length=50,nullable = false)
	private String brand;
	

	@Column(nullable = false)
	private Long number;
	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "goodsclass")
	private Set<Goods> goods = new HashSet<>();

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

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}
	
	
	

}
