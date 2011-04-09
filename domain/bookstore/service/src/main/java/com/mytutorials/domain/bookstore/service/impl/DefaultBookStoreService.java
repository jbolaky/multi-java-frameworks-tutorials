package com.mytutorials.domain.bookstore.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.guard.Guarded;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;
import com.mytutorials.domain.bookstore.repository.api.BookStoreRepository;
import com.mytutorials.domain.bookstore.service.api.BookStoreService;

@Guarded(inspectInterfaces = true)
@Service(value = "default_BookStore_Service")
public class DefaultBookStoreService implements BookStoreService {

	@Resource(name = "default_BookStore_Repository")
	private BookStoreRepository bookStoreRepository;

	public Book persist(Book book) {
		return bookStoreRepository.persist(book);
	}

	public Book findById(Long id) {
		return bookStoreRepository.findById(id);
	}

	public Book merge(Book book) {
		return bookStoreRepository.merge(book);
	}

	public List<Book> findByCreationDateTime(LocalDate from, LocalDate to) {
		return bookStoreRepository.findByCreationDateTime(from, to);
	}

	public void flush() {
		bookStoreRepository.flush();
	}

}
