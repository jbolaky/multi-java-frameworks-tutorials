package com.mytutorials.domain.bookstore.entity.mapping.api;

import java.io.Serializable;
import java.util.Set;

public interface Book extends TimestampUsername, Serializable {

	Long getId();

	String getTitle();

	Set<Author> getAuthors();

	Integer getTotalNumberOfPages();

	String getEdition();

	void addAuthor(Author author);
}
