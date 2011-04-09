package com.mytutorials.spring.oval.entity;

public class Person {
	private String sex;
	private String name;

	public Person(String name, String sex) {
		this.name = name;
		this.name = sex;
	}

	public Person() {
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
