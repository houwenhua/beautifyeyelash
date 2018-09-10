package com.meijie.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Salary {

	@GeneratedValue
	@Id
	private Integer id;
	
	@Column(nullable = false)
	private BigDecimal minsalary;
	
	@Column(nullable = false)
	private BigDecimal reward;
	
	@Column(nullable = false)
	private BigDecimal summoney;
	
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER, mappedBy = "salary")
	private Set<SalaryDetail> salaryDetail = new HashSet<>();
	
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "waiterid")
	private Waiter waiter;
	
	

	public Salary() {
		super();
	}

	public Salary(BigDecimal minsalary, BigDecimal reward, BigDecimal summoney, Waiter waiter) {
		super();
		this.minsalary = minsalary;
		this.reward = reward;
		this.summoney = summoney;
		this.waiter = waiter;
	}

	public Salary(Integer id, BigDecimal minsalary, BigDecimal reward, BigDecimal summoney, Waiter waiter) {
		super();
		this.id = id;
		this.minsalary = minsalary;
		this.reward = reward;
		this.summoney = summoney;
		this.waiter = waiter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getMinsalary() {
		return minsalary;
	}

	public void setMinsalary(BigDecimal minsalary) {
		this.minsalary = minsalary;
	}

	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}

	public BigDecimal getSummoney() {
		return summoney;
	}

	public void setSummoney(BigDecimal summoney) {
		this.summoney = summoney;
	}

	public Set<SalaryDetail> getSalaryDetail() {
		return salaryDetail;
	}

	public void setSalaryDetail(Set<SalaryDetail> salaryDetail) {
		this.salaryDetail = salaryDetail;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	
}
