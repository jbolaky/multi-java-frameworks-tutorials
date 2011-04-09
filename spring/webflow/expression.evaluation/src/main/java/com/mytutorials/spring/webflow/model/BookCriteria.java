package com.mytutorials.spring.webflow.model;

import java.io.Serializable;

public class BookCriteria implements Serializable {

	private static final long serialVersionUID = -2449120857018374251L;

	private String keyword;
	private String author;

	public String getKeyword() {
		return keyword;
	}

	public String getAuthor() {
		return author;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
