package com.mytutorial.spring.cache.impl;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mytutorial.spring.cache.api.BookStoreService;
import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

public class DefaultBookStoreService implements BookStoreService {

	@Autowired
	@Qualifier("default_BookStore_Service")
	private com.mytutorials.domain.bookstore.service.api.BookStoreService bookStoreService;

	public com.mytutorials.domain.bookstore.service.api.BookStoreService getBookStoreService() {
		return bookStoreService;
	}

	public Book persist(Book book) {

		book = bookStoreService.persist(book);
		bookStoreService.flush();
		return book;
	}

	public Book findById(Long id) {

		return bookStoreService.findById(id);
	}

	public Book merge(Book book) {

		book = bookStoreService.merge(book);
		bookStoreService.flush();
		return book;
	}

	public List<Book> findByCreationDateTimeWithCacheable(LocalDate from,
			LocalDate to) {
		return bookStoreService.findByCreationDateTime(from, to);
	}

	public List<Book> findByCreationDateTimeWithOutCacheable(LocalDate from,
			LocalDate to) {
		return bookStoreService.findByCreationDateTime(from, to);
	}

}
