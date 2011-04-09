package com.mytutorials.ejb3.jboss.stateful.api;

import java.util.List;

import com.mytutorials.ejb3.jboss.stateful.vo.Book;

public interface BookStoreService {

	Book addBook(Book book);

	List<Book> getAllBooks();
}
