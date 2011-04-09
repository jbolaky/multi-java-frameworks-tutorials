package com.mytutorials.ejb.jboss.stateless.client.api;

import java.util.List;

import com.mytutorials.ejb3.jboss.stateless.vo.Book;

public interface BookStoreServiceClient {

	Book addBook(Book book);

	List<Book> getAllBooks();
}
