package com.mytutorials.spring.mvc.webapp.domain.entity;

public class Player {

	private String name;
	private String phone;
	
	public Player(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", phone=" + phone + "]";
	}

}
