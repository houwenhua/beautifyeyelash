package com.meijie.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Consume {
	
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
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="vipid",nullable=true)
	private Vip vip;
	
	/**
	 * 服务人
	 * */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="waiterid",nullable=true)
	private Waiter waiter;
	
	/**
	 * 服务类型
	 * */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="serviceid",nullable=true)
	private Service service;
	
	/**
	 * 消费日期
	 * */
	@Column(name = "date", nullable = false)  
	private String date;
	
	/**
	 * 收取的积分
	 * */
	@Column(name = "integral", nullable = false)  
	private String integral;

	public Consume() {
		super();
	}


	public Consume(BigDecimal money, Vip vip, Waiter waiter, Service service, String date, String integral) {
		super();
		this.money = money;
		this.vip = vip;
		this.waiter = waiter;
		this.service = service;
		this.date = date;
		this.integral = integral;
	}


	public Consume(Integer id, BigDecimal money, Vip vip, Waiter waiter, Service service, String date,
			String integral) {
		super();
		this.id = id;
		this.money = money;
		this.vip = vip;
		this.waiter = waiter;
		this.service = service;
		this.date = date;
		this.integral = integral;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
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

	

	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

}
