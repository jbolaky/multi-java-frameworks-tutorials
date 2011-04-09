package com.mytutorials.domain.bookstore.repository.api;

import java.util.List;

import org.joda.time.LocalDate;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

public interface BookStoreRepository {

	Book persist(Book book);
	
	Book findById(Long id);
	
	Book merge(Book book);
	
	List<Book> findByCreationDateTime(LocalDate from, LocalDate to);
	
	void flush();
}
