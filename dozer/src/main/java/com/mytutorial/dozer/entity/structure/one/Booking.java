package com.mytutorial.dozer.entity.structure.one;

import java.util.ArrayList;
import java.util.List;

public class Booking {

	private String firstname;
	private String lastname;
	private String gender;
	private Integer telephoneNumber;
	private List<Item> items = new ArrayList<Item>();
	private boolean bookingConfirm = false;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getGender() {
		return gender;
	}

	public Integer getTelephoneNumber() {
		return telephoneNumber;
	}

	public List<Item> getItems() {
		return items;
	}

	public boolean isBookingConfirm() {
		return bookingConfirm;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setTelephoneNumber(Integer telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public void addItem(Item item) {
		items.remove(item);
		this.items.add(item);
	}

	public void setBookingConfirm(boolean bookingConfirm) {
		this.bookingConfirm = bookingConfirm;
	}

}
