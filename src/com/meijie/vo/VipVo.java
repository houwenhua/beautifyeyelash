package com.meijie.vo;

public class VipVo {

	private int id;
	
	private String name;
	
	private String phone;
	
	private int age;
	
	private String sex;
	
	private String remark;
	
	/**
	 * 总积分
	 */
	private String integral = "0";

	public VipVo() {
		super();
	}

	public VipVo(int id, String name, String phone, int age, String sex, String remark, String integral) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.sex = sex;
		this.remark = remark;
		this.integral = integral;
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

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
}
