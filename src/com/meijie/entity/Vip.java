package com.meijie.entity;

import java.util.HashSet;
import java.util.Set;

public class Vip {

	private int id;
	private String name;
	private String phone;
	private int age;
	private String sex;
	private String remark;
	
	private Set<Consume> consumes = new HashSet<Consume>();
	public Vip() {
		super();
	}
	public Vip(int id, String name, String phone, int age, String sex, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.remark = remark;
	}
	public Vip(String name, String phone, int age, String sex, String remark) {
		super();
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.remark = remark;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Set<Consume> getConsumes() {
		return consumes;
	}
	public void setConsumes(Set<Consume> consumes) {
		this.consumes = consumes;
	}
	
}
