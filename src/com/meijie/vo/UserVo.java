package com.meijie.vo;

public class UserVo {

private int id;
	
	private String name;
	
	private String loginname;
	
	private String password;
	
	private String email;
	
	private String jurisdiction;
	
	private int state;
	
	private String store;
	
	

	public UserVo() {
		super();
	}

	public UserVo(int id, String name, String loginname, String password, String email, String jurisdiction, int state,
			String store) {
		super();
		this.id = id;
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.jurisdiction = jurisdiction;
		this.state = state;
		this.store = store;
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	
	
}
