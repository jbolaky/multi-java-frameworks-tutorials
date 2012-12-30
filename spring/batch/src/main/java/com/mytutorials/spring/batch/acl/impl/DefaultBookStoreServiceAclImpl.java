package com.mytutorials.spring.batch.acl.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import com.mytutorials.domain.bookstore.service.api.BookStoreService;
import com.mytutorials.spring.batch.acl.api.BookStoreServiceAcl;
import com.mytutorials.spring.batch.domain.Book;

public class DefaultBookStoreServiceAclImpl implements BookStoreServiceAcl {

	@Autowired
	private BookStoreService bookStoreService;

	@Autowired
	@Qualifier("springBatchBookConverter")
	private Converter<Book, com.mytutorials.domain.bookstore.entity.mapping.api.Book> springBatchBookConverter;

	@Autowired
	@Qualifier("bookStoreServiceBookConverter")
	private Converter<com.mytutorials.domain.bookstore.entity.mapping.api.Book, Book> bookStoreServiceBookConverter;

	public Book persist(Book book) {

		return bookStoreServiceBookConverter.convert(bookStoreService
				.persist(springBatchBookConverter.convert(book)));
	}

}
