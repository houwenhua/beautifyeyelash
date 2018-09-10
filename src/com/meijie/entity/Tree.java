package com.meijie.entity;

public class Tree {

	private int id;
	private String text;
	private String state;
	private String iconCls;
	private String url;
	private int tid;
	
	public Tree() {
		super();
	}
	public Tree(int id, String text, String state, String iconCls, String url, int tid) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
		this.url = url;
		this.tid = tid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
}
