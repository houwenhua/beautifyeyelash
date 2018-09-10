package com.meijie.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Goods {

	/**
	 * 商品id
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 商品名
	 */
	@Column(length=50,nullable = false)
	private String name;
	
	/**
	 * 进价
	 */
	@Column(nullable = false)
	private BigDecimal ginprice;
	
	/**
	 * 售价
	 */
	@Column(nullable = false)
	private BigDecimal goutprivce;
	
	/**
	 * 生产日期
	 */
	@Column(nullable = true)
	private String productiondate = "暂无";
	
	/**
	 * 进购数量
	 */
	@Column(nullable = false)
	private Long productiontotal;
	
	/**
	 * 供货商
	 */
	@Column(length=50,nullable = true)
	private String supplier = "暂无";
	
	/**
	 * 采购时间
	 */
	@Column(length=50,nullable = false)
	private String storagetime;
	
	@Column(length=50,nullable = true)
	private String brand;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="purchaseid")
	private Purchase purchase;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="goodsclassid")
	private GoodsClass goodsclass;
	
	
	public Goods() {
		super();
	}

	public Goods(String name, BigDecimal ginprice, BigDecimal goutprivce, String productiondate, Long productiontotal,
			String supplier, String storagetime, Purchase purchase,String brand) {
		super();
		this.name = name;
		this.ginprice = ginprice;
		this.goutprivce = goutprivce;
		this.productiondate = productiondate;
		this.productiontotal = productiontotal;
		this.supplier = supplier;
		this.storagetime = storagetime;
		this.purchase = purchase;
		this.brand = brand;
	}
	
	

	public Goods(Integer id, String name, BigDecimal ginprice, BigDecimal goutprivce, String productiondate,
			Long productiontotal, String supplier, String storagetime, Purchase purchase,String brand) {
		super();
		this.id = id;
		this.name = name;
		this.ginprice = ginprice;
		this.goutprivce = goutprivce;
		this.productiondate = productiondate;
		this.productiontotal = productiontotal;
		this.supplier = supplier;
		this.storagetime = storagetime;
		this.purchase = purchase;
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

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
