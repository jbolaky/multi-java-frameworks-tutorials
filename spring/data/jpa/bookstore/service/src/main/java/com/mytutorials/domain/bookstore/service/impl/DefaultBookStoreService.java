package com.mytutorials.domain.bookstore.service.impl;

import java.util.List;

import net.sf.oval.guard.Guarded;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;
import com.mytutorials.domain.bookstore.repository.api.DataJPABookStoreRepository;
import com.mytutorials.domain.bookstore.service.api.BookStoreService;

@Guarded(inspectInterfaces = true)
@Service(value = "default_BookStore_Service")
public class DefaultBookStoreService implements BookStoreService {

	@Autowired
	private DataJPABookStoreRepository bookStoreRepository;

	public Book persist(Book book) {
		return bookStoreRepository.save((DefaultBook) book);
	}

	public Book findById(Long id) {
		return bookStoreRepository.findOne(id);
	}

	public Book merge(Book book) {
		return bookStoreRepository.save((DefaultBook) book);
	}

	public List<Book> findByCreationDateTime(LocalDate from, LocalDate to) {
		return null;
		/* return bookStoreRepository.findByCreationDateTime(from, to); */
	}

	public void flush() {
		bookStoreRepository.flush();
	}

}
