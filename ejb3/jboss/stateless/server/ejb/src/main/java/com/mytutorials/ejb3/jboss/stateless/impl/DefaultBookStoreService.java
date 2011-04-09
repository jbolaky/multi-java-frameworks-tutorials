package com.mytutorials.ejb3.jboss.stateless.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.mytutorials.ejb3.jboss.stateless.api.BookStoreService;
import com.mytutorials.ejb3.jboss.stateless.vo.Book;

@Stateless
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
