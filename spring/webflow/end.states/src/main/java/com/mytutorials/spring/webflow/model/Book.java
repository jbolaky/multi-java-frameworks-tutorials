package com.mytutorials.spring.webflow.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {

	private static final long serialVersionUID = -7837983924276748690L;

	private String isbn;
	private String name;
	private String author;
	private Date publishDate;
	
	public Book(String isbn, String name, String author, Date publishDate) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.publishDate = publishDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}