package com.javaid.tutorials.oval.entity;
import java.util.Date;

import net.sf.oval.constraint.AssertTrue;
import net.sf.oval.constraint.AssertURL;
import net.sf.oval.constraint.DateRange;
import net.sf.oval.constraint.EqualToField;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;

public class Customer {
	@Length(min = 2, max = 20, errorCode = "Customer.firstName.error.lenght")
	private String firstName;
	
	@MinLength(value = 2, errorCode = "Customer.lastName.error.minLength")
	@MaxLength(value = 20, errorCode = "Customer.lastName.error.maxLength")
	private String lastName;
	
	@MatchPattern(pattern = "[a-zA-Z0-9]{4,}", errorCode = "Customer.password.error.matchPattern")
	private String password;
	
	@EqualToField(value = "password", errorCode = "Customer.passwordVerification.error.equalToField")
	private String passwordVerification;
	
	@DateRange(max = "now", min = "1900-01-01", format = "yyyy-MM-DD", errorCode = "Customer.birthDate.error.dateRange")
	private Date birthDate;
	
	@MatchPattern(pattern = "[0-9]{11}", errorCode = "Customer.pesel.error.matchPattern")
	private String PESEL;
	
	@AssertURL(connect = true, errorCode = "Customer.homePage.error.invalidURL")
	private String homePage;
	
	private boolean justAFlag = true;

	@AssertTrue(errorCode = "Customer.pesel.error.crossValidation")
	public boolean isValidPESEL() {
		return justAFlag;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getPasswordVerification() {
		return passwordVerification;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getPESEL() {
		return PESEL;
	}

	public String getHomePage() {
		return homePage;
	}

	public boolean isJustAFlag() {
		return justAFlag;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setPESEL(String pESEL) {
		PESEL = pESEL;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public void setJustAFlag(boolean justAFlag) {
		this.justAFlag = justAFlag;
	}
	
}