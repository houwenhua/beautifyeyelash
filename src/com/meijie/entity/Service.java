package com.meijie.entity;

import java.util.HashSet;
import java.util.Set;

public class Service {

	private int id;
	private String servicename;
	private String imagepath;
	private double price;
	private double vipprice;
	private String remark;
	
	/**
	 * 会员消费记录
	 * */
	private Set<Consume> consumes = new HashSet<Consume>();
	
	/**
	 * 游客消费记录
	 */
	private Set<Visitor> visitors = new HashSet<Visitor>();
	
	/**
	 * 材料信息
	 */
	private Set<Material> materials = new HashSet<Material>();
	
	
	public Service() {
		super();
	}
	public Service(int id, String servicename, double price, double vipprice, String remark) {
		super();
		this.id = id;
		this.servicename = servicename;
		this.price = price;
		this.vipprice = vipprice;
		this.remark = remark;
	}
	public Service(String servicename, String imagepath, double price, double vipprice, String remark) {
		super();
		this.servicename = servicename;
		this.imagepath = imagepath;
		this.price = price;
		this.vipprice = vipprice;
		this.remark = remark;
	}
	public Service(int id, String servicename, String imagepath, double price, double vipprice, String remark) {
		super();
		this.id = id;
		this.servicename = servicename;
		this.imagepath = imagepath;
		this.price = price;
		this.vipprice = vipprice;
		this.remark = remark;
	}
	public Service(String servicename, String imagepath, double price, double vipprice, String remark,
			Set<Material> materials) {
		super();
		this.servicename = servicename;
		this.imagepath = imagepath;
		this.price = price;
		this.vipprice = vipprice;
		this.remark = remark;
		this.materials = materials;
	}
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
	public Set<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}
	
}
