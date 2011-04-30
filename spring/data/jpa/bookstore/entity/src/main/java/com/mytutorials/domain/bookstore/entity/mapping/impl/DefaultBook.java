package com.mytutorials.domain.bookstore.entity.mapping.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import com.mytutorials.domain.bookstore.entity.mapping.api.Author;
import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

@Entity
@Table(name = "BKS_BOOK")
@TypeDef(name = "hibernate_persistentDateTime", typeClass = org.joda.time.contrib.hibernate.PersistentDateTime.class)
@NamedQuery(name = "DefaultBook.findByEdition", query = "select b from DefaultBook b where b.edition = ?1")
public class DefaultBook extends AbstractTimestampUsernameEntity implements
		Book {

	private static final long serialVersionUID = 6733167892181528743L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book", targetEntity = DefaultAuthor.class)
	private Set<Author> authors = new HashSet<Author>();

	@Column(name = "TOTAL_PAGES")
	private Integer totalNumberOfPages;

	@Column(name = "EDITION")
	private String edition;

	public Long getId() {
		return id;
	}

	public boolean isNew() {
		return id == null ? true : false;
	}

	public String getTitle() {
		return title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public String getEdition() {
		return edition;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public void addAuthor(Author author) {
		this.authors.add(author);
	}

	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

}
