package com.meijie.entity;

import java.util.HashSet;
import java.util.Set;

public class Waiter {

	private int id;
	private String name;
	private String phone;
	private String sex;
	private int age;
	private Double salary;
	private String job;
	private int storeid;
	
	/**
	 * 会员消费记录
	 * */
	private Set<Consume> consumes = new HashSet<Consume>();
	
	/**
	 * 游客消费记录
	 */
	private Set<Visitor> visitors = new HashSet<Visitor>();
	
	/**
	 * 商品消费记录
	 */
	private Set<GoodsRecord> goodsrecords = new HashSet<GoodsRecord>();
	
	public Waiter() {
		super();
	}
	public Waiter(String name, String phone, String sex, int age, Double salary, int storeid,String job) {
		super();
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
		this.storeid = storeid;
		this.job =job;
	}
	public Waiter(int id, String name, String phone, String sex, int age, Double salary, int storeid,String job) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.salary = salary;
		this.storeid = storeid;
		this.job =job;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public Set<Consume> getConsumes() {
		return consumes;
	}
	public void setConsumes(Set<Consume> consumes) {
		this.consumes = consumes;
	}
	public Set<Visitor> getVisitors() {
		return visitors;
	}
	public void setVisitors(Set<Visitor> visitors) {
		this.visitors = visitors;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Set<GoodsRecord> getGoodsrecords() {
		return goodsrecords;
	}
	public void setGoodsrecords(Set<GoodsRecord> goodsrecords) {
		this.goodsrecords = goodsrecords;
	}
}
