package com.mytutorial.spring.dozer.entity.structure.one;

import java.util.Date;

public class StatusRecord {

	private StatusRecordKey statusRecordKey;

	private Date creationDate;

	public StatusRecordKey getStatusRecordKey() {
		return statusRecordKey;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setStatusRecordKey(StatusRecordKey statusRecordKey) {
		this.statusRecordKey = statusRecordKey;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
