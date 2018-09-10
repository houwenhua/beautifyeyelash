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
public class Visitor {

	@GeneratedValue
	@Id
	private Integer id;
	
	/**
	 * 消费金额
	 * */
	@Column(name = "money", nullable = false)  
	private BigDecimal money;
	
	/**
	 * 消费人
	 * */
	@Column(name = "name", nullable = false)  
	private String name;
	
	/**
	 * 服务人
	 * */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="waiterid")
	private Waiter waiter;
	
	/**
	 * 服务类型
	 * */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="serviceid")
	private Service service;
	
	/**
	 * 消费日期
	 * */
	@Column(name = "date", nullable = false)  
	private String date;
	
	

	public Visitor() {
		super();
	}

	public Visitor(BigDecimal money, String name, Waiter waiter, Service service, String date) {
		super();
		this.money = money;
		this.name = name;
		this.waiter = waiter;
		this.service = service;
		this.date = date;
	}

	public Visitor(Integer id, BigDecimal money, String name, Waiter waiter, Service service, String date) {
		super();
		this.id = id;
		this.money = money;
		this.name = name;
		this.waiter = waiter;
		this.service = service;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
