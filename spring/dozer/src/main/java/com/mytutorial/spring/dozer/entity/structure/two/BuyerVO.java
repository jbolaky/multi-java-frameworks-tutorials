package com.mytutorial.spring.dozer.entity.structure.two;

import com.mytutorial.spring.dozer.entity.structure.two.enumeration.Gender;

public class BuyerVO {

	private String firstname;
	private String lastname;
	private Gender gender;
	private String telephoneNumber;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Gender getGender() {
		return gender;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

}
