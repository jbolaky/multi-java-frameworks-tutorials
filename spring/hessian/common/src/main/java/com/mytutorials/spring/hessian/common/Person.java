package com.mytutorials.spring.hessian.common;

import java.io.Serializable;
import java.util.Calendar;

public class Person implements Serializable {

	private static final long serialVersionUID = -7986915924153719770L;

	private Integer id;
	private String firstName;
	private String lastName;
	private Calendar birthDate;

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, Calendar birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	/**
	 * @return Primary key
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthDate=" + birthDate + "]";
	}
	
	
}
