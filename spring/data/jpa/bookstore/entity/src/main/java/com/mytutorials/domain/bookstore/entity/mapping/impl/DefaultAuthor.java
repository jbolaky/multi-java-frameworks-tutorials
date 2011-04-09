package com.mytutorials.domain.bookstore.entity.mapping.impl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.TypeDef;

import com.mytutorials.domain.bookstore.entity.mapping.api.Author;
import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

@Entity(name = "BKS_AUTHOR")
@TypeDef(name = "hibernate_persistentDateTime", typeClass = org.joda.time.contrib.hibernate.PersistentLocalDateTime.class)
public class DefaultAuthor extends AbstractTimestampUsernameEntity implements
		Author {

	private static final long serialVersionUID = 5194778448413834441L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = DefaultBook.class)
	@JoinColumn(name = "BOOK_ID")
	private Book book;

	protected DefaultAuthor() {
		super();
	}

	public DefaultAuthor(Book book) {
		super();
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Book getBook() {
		return book;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
