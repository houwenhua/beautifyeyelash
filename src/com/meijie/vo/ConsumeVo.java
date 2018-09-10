package com.meijie.vo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ConsumeVo {


	private Integer id;
	
	/**
	 * 消费金额
	 * */
	private BigDecimal money;
	
	/**
	 * 消费人
	 * */
	private String vipname;
	
	/**
	 * 服务人
	 * */
	private String waitername;
	
	/**
	 * 服务类型
	 * */
	private String servicename;
	
	/**
	 * 消费日期
	 * */
	private String date;
	
	/**
	 * 收取的积分
	 * */
	private String integral;
	
	private Integer serviceid;
	private Integer vipid;
	private Integer waiterid;
	public ConsumeVo() {
		super();
	}

	public ConsumeVo(Integer id, BigDecimal money, String vipname, String waitername, String servicename, String date,
			String integral) {
		super();
		this.id = id;
		this.money = money;
		this.vipname = vipname;
		this.waitername = waitername;
		this.servicename = servicename;
		this.date = date;
		this.integral = integral;
	}
	
	

	public ConsumeVo(Integer id, BigDecimal money, String vipname, String waitername, String servicename, String date) {
		super();
		this.id = id;
		this.money = money;
		this.vipname = vipname;
		this.waitername = waitername;
		this.servicename = servicename;
		this.date = date;
	}
	

	public ConsumeVo(Integer id, BigDecimal money, String vipname, String waitername, String servicename, String date,
			Integer serviceid, Integer waiterid) {
		super();
		this.id = id;
		this.money = money;
		this.vipname = vipname;
		this.waitername = waitername;
		this.servicename = servicename;
		this.date = date;
		this.serviceid = serviceid;
		this.waiterid = waiterid;
	}

	public ConsumeVo(Integer id, BigDecimal money, String vipname, String waitername, String servicename, String date,
			String integral, Integer serviceid, Integer vipid, Integer waiterid) {
		super();
		this.id = id;
		this.money = money;
		this.vipname = vipname;
		this.waitername = waitername;
		this.servicename = servicename;
		this.date = date;
		this.integral = integral;
		this.serviceid = serviceid;
		this.vipid = vipid;
		this.waiterid = waiterid;
	}

	public Integer getServiceid() {
		return serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getVipid() {
		return vipid;
	}

	public void setVipid(Integer vipid) {
		this.vipid = vipid;
	}

	public Integer getWaiterid() {
		return waiterid;
	}

	public void setWaiterid(Integer waiterid) {
		this.waiterid = waiterid;
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

	public String getVipname() {
		return vipname;
	}

	public void setVipname(String vipname) {
		this.vipname = vipname;
	}

	public String getWaitername() {
		return waitername;
	}

	public void setWaitername(String waitername) {
		this.waitername = waitername;
	}

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	
}
