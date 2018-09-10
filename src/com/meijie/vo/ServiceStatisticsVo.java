package com.meijie.vo;

import java.math.BigDecimal;

public class ServiceStatisticsVo {

	/**
	 * 横坐标名
	 */
	private String name;
	
	/**
	 * 坐标值
	 */
	//private Integer value;
	
	private BigDecimal value;
	
	/**
	 * 柱形颜色
	 * 默认颜色：#6f83a5
	 */
	private String color = "#6f83a5";
	
	public ServiceStatisticsVo() {
		super();
	}
	
	public ServiceStatisticsVo(String name, BigDecimal value, String color) {
		super();
		this.name = name;
		this.value = value;
		this.color = color;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public BigDecimal getValue() {
		return value;
	}



	public void setValue(BigDecimal value) {
		this.value = value;
	}



	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
