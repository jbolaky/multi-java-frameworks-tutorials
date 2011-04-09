package com.mytutorials.ejb.jboss.stateful.client.api;

import java.util.List;

import com.mytutorials.ejb3.jboss.stateful.vo.Book;

public interface BookStoreServiceClient {

	Book addBook(Book book);

	List<Book> getAllBooks();
}
