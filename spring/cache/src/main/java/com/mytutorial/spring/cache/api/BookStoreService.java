package com.mytutorial.spring.cache.api;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.cache.annotation.Cacheable;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

public interface BookStoreService {

	Book persist(Book book);

	Book findById(Long id);

	Book merge(Book book);

	@Cacheable(value = "defaultCache", key = "#from.getCenturyOfEra().toString().concat(#to.getCenturyOfEra().toString())")
	List<Book> findByCreationDateTimeWithCacheable(LocalDate from, LocalDate to);

	List<Book> findByCreationDateTimeWithOutCacheable(LocalDate from,
			LocalDate to);
}
