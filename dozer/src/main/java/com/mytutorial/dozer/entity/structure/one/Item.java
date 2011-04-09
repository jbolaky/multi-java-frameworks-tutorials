package com.mytutorial.dozer.entity.structure.one;

public class Item {

	private Long itemReferenceNumber;
	
	private String itemName;
	
	public Item(Long itemReferenceNumber, String itemName) {
		super();
		this.itemReferenceNumber = itemReferenceNumber;
		this.itemName = itemName;
	}

	public Long getItemReferenceNumber() {
		return itemReferenceNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemReferenceNumber(Long itemReferenceNumber) {
		this.itemReferenceNumber = itemReferenceNumber;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "Item [itemReferenceNumber=" + itemReferenceNumber + ", itemName=" + itemName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemReferenceNumber == null) ? 0 : itemReferenceNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemReferenceNumber == null) {
			if (other.itemReferenceNumber != null)
				return false;
		} else if (!itemReferenceNumber.equals(other.itemReferenceNumber))
			return false;
		return true;
	}
	
}
