package com.mytutorials.domain.bookstore.service.api;

import java.util.List;

import net.sf.oval.constraint.NotNull;

import org.joda.time.LocalDate;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

public interface BookStoreService {

	Book persist(Book book);

	Book findById(@NotNull Long id);

	Book merge(Book book);

	List<Book> findByCreationDateTime(@NotNull LocalDate from,
			@NotNull LocalDate to);

	void flush();

}
