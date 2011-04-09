package com.mytutorials.spring.webflow.service.api;

import java.util.List;

import com.mytutorials.spring.webflow.model.Book;
import com.mytutorials.spring.webflow.model.BookCriteria;

public interface BookService {

	List<Book> search(BookCriteria criteria);
	Book findByIsbn(String isbn);

}
