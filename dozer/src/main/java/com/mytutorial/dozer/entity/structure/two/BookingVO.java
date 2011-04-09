package com.mytutorial.dozer.entity.structure.two;

import java.util.ArrayList;
import java.util.List;

public class BookingVO {

	private BuyerVO buyerVO;

	private List<String> bookedItems = new ArrayList<String>();

	private Boolean confirm;

	public Boolean getConfirm() {
		return confirm;
	}

	public BuyerVO getBuyerVO() {
		return buyerVO;
	}

	public List<String> getBookedItems() {
		return bookedItems;
	}

	public void setBuyerVO(BuyerVO buyerVO) {
		this.buyerVO = buyerVO;
	}

	public void addBookedItem(String bookedItem) {
		this.bookedItems.remove(bookedItem);
		this.bookedItems.add(bookedItem);
	}

	public void setConfirm(Boolean confirm) {
		this.confirm = confirm;
	}

}
