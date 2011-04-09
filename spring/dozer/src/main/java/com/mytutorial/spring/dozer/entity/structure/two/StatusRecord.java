package com.mytutorial.spring.dozer.entity.structure.two;

import java.util.Date;

public class StatusRecord {

	private Long id;
	
	private String code;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Date getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
