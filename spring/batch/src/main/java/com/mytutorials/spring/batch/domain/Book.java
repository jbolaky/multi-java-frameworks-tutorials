package com.mytutorials.spring.batch.domain;

import java.util.HashSet;
import java.util.Set;

public class Book {

	private String title;

	private Set<Author> authors = new HashSet<Author>();

	private Integer totalNumberOfPages;

	private String edition;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public boolean addAuthors(Author author) {
		
		return authors.add(author);
	}

	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", authors=" + authors
				+ ", totalNumberOfPages=" + totalNumberOfPages + ", edition="
				+ edition + "]";
	}
	
}
