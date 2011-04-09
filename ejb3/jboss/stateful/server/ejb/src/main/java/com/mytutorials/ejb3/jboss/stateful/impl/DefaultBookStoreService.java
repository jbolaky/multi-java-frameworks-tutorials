package com.mytutorials.ejb3.jboss.stateful.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import com.mytutorials.ejb3.jboss.stateful.api.BookStoreService;
import com.mytutorials.ejb3.jboss.stateful.vo.Book;

@Stateful
@Remote(BookStoreService.class)
public class DefaultBookStoreService implements BookStoreService {

	private List<Book> books = new ArrayList<Book>();

	public Book addBook(Book book) {
		if (books.add(book)) {
			return book;
		}
		return null;
	}

	public List<Book> getAllBooks() {
		return books;
	}

}
