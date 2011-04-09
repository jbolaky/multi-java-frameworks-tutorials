package com.mytutorials.ejb3.jboss.stateless.api;

import java.util.List;

import com.mytutorials.ejb3.jboss.stateless.vo.Book;

public interface BookStoreService {

	Book addBook(Book book);

	List<Book> getAllBooks();
}
