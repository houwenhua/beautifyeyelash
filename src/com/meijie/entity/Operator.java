package com.meijie.entity;

public class Operator {

	private int id;
	private String name;
	private String phone;
	private String sex;
	private int age;
	private int storeid;
	public Operator() {
		super();
	}
	public Operator(int id, String name, String phone, String sex, int age, int storeid) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.storeid = storeid;
	}
	public Operator(String name, String phone, String sex, int age, int storeid) {
		super();
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.age = age;
		this.storeid = storeid;
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
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	
}
