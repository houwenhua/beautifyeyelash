package com.meijie.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SalaryDetail {

	@GeneratedValue
	@Id
	private Integer id;
	
	/**
	 * 消费日期
	 */
	@Column(nullable = false, length = 30)
	private String date;
	
	/**
	 * 消费总金额
	 */
	@Column(nullable = false)
	private BigDecimal allmoney;
	
	/**
	 * 提成比例
	 */
	@Column(nullable = false)
	private BigDecimal rate;
	
	/**
	 * 提成金额
	 */
	@Column(nullable = false)
	private BigDecimal reward;
	
	/**
	 * 状态，用于表示是否是上个月的记录
	 */
	@Column(nullable = false, length = 1)
	private String state;
	
	/**
	 * 与会员消费记录一对一
	 */
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "consumeid")
	private Consume consume;
	
	/**
	 * 与游客消费记录一对一
	 */
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "visitorid")
	private Visitor visitor;
	
	/**
	 * 与商品消费记录一对一
	 */
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "grid")
	private GoodsRecord gr;
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "salaryid")
	private Salary salary;
	
	

	public SalaryDetail() {
		super();
	}

	public SalaryDetail(String date, BigDecimal allmoney, BigDecimal rate, BigDecimal reward, String state,
			GoodsRecord gr, Salary salary) {
		super();
		this.date = date;
		this.allmoney = allmoney;
		this.rate = rate;
		this.reward = reward;
		this.state = state;
		this.gr = gr;
		this.salary = salary;
	}

	public SalaryDetail(String date, BigDecimal allmoney, BigDecimal rate, BigDecimal reward, String state,
			Consume consume,Salary salary) {
		super();
		this.date = date;
		this.allmoney = allmoney;
		this.rate = rate;
		this.reward = reward;
		this.state = state;
		this.consume = consume;
		this.salary = salary;
	}

	public SalaryDetail(String date, BigDecimal allmoney, BigDecimal rate, BigDecimal reward, String state,
			Visitor visitor, Salary salary) {
		super();
		this.date = date;
		this.allmoney = allmoney;
		this.rate = rate;
		this.reward = reward;
		this.state = state;
		this.visitor = visitor;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Consume getConsume() {
		return consume;
	}

	public void setConsume(Consume consume) {
		this.consume = consume;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public GoodsRecord getGr() {
		return gr;
	}

	public void setGr(GoodsRecord gr) {
		this.gr = gr;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	
	
}
