package com.meijie.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.meijie.entity.Purchase;

public class GoodsVo {

	/**
	 * 商品id
	 */
	private Integer id;
	
	/**
	 * 商品名
	 */
	private String name;
	
	/**
	 * 进价
	 */
	private BigDecimal ginprice;
	
	/**
	 * 售价
	 */
	private BigDecimal goutprivce;
	
	/**
	 * 生产日期
	 */
	private String productiondate = "暂无";
	
	/**
	 * 进购数量
	 */
	private Long productiontotal;
	
	/**
	 * 供货商
	 */
	private String supplier = "暂无";
	
	private String storagetime;
	
	private String brand;
	
	private Integer purid;

	public GoodsVo() {
		super();
	}

	public GoodsVo(String name, BigDecimal ginprice, BigDecimal goutprivce, String productiondate, Long productiontotal,
			String supplier, String storagetime) {
		super();
		this.name = name;
		this.ginprice = ginprice;
		this.goutprivce = goutprivce;
		this.productiondate = productiondate;
		this.productiontotal = productiontotal;
		this.supplier = supplier;
		this.storagetime = storagetime;
	}

	public GoodsVo(Integer id, String name, BigDecimal ginprice, BigDecimal goutprivce, String productiondate,
			Long productiontotal, String supplier, String storagetime,String brand) {
		super();
		this.id = id;
		this.name = name;
		this.ginprice = ginprice;
		this.goutprivce = goutprivce;
		this.productiondate = productiondate;
		this.productiontotal = productiontotal;
		this.supplier = supplier;
		this.storagetime = storagetime;
		this.brand = brand;
	}
	

	public GoodsVo(Integer id, String name, BigDecimal ginprice, BigDecimal goutprivce, String productiondate,
			Long productiontotal, String supplier, String storagetime, Integer purid,String brand) {
		super();
		this.id = id;
		this.name = name;
		this.ginprice = ginprice;
		this.goutprivce = goutprivce;
		this.productiondate = productiondate;
		this.productiontotal = productiontotal;
		this.supplier = supplier;
		this.storagetime = storagetime;
		this.purid = purid;
		this.brand = brand;
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

	public BigDecimal getGinprice() {
		return ginprice;
	}

	public void setGinprice(BigDecimal ginprice) {
		this.ginprice = ginprice;
	}

	public BigDecimal getGoutprivce() {
		return goutprivce;
	}

	public void setGoutprivce(BigDecimal goutprivce) {
		this.goutprivce = goutprivce;
	}

	public String getProductiondate() {
		return productiondate;
	}

	public void setProductiondate(String productiondate) {
		this.productiondate = productiondate;
	}

	public Long getProductiontotal() {
		return productiontotal;
	}

	public void setProductiontotal(Long productiontotal) {
		this.productiontotal = productiontotal;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getStoragetime() {
		return storagetime;
	}

	public void setStoragetime(String storagetime) {
		this.storagetime = storagetime;
	}

	public Integer getPurid() {
		return purid;
	}

	public void setPurid(Integer purid) {
		this.purid = purid;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	
}
