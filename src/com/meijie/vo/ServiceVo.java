package com.meijie.vo;

public class ServiceVo {

	private int id;
	private String servicename;
	private String imagepath;
	private double price;
	private double vipprice;
	private String material;
	private String remark;
	/**
	 * 图片的物理路径
	 * */
	private String realpicturepath;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getVipprice() {
		return vipprice;
	}
	public void setVipprice(double vipprice) {
		this.vipprice = vipprice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRealpicturepath() {
		return realpicturepath;
	}
	public void setRealpicturepath(String realpicturepath) {
		this.realpicturepath = realpicturepath;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
}
