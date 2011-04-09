package com.mytutorial.spring.dozer.entity.structure.two;

import java.util.ArrayList;
import java.util.List;

public class BookingVO {

	private BuyerVO buyerVO;

	private List<String> bookedItems = new ArrayList<String>();

	private Boolean confirm;

	private StatusRecord[] statusRecords;

	public Boolean getConfirm() {
		return confirm;
	}

	public BuyerVO getBuyerVO() {
		return buyerVO;
	}

	public List<String> getBookedItems() {
		return bookedItems;
	}

	public StatusRecord[] getStatusRecords() {
		return statusRecords;
	}

	public void setBuyerVO(BuyerVO buyerVO) {
		this.buyerVO = buyerVO;
	}

	public void setBookedItems(List<String> bookedItems) {
		this.bookedItems = bookedItems;
	}

	public void addBookedItem(String bookedItem) {
		this.bookedItems.remove(bookedItem);
		this.bookedItems.add(bookedItem);
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

	public void setStatusRecords(StatusRecord[] statusRecords) {
		this.statusRecords = statusRecords;
	}

}
