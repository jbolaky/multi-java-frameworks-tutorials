package com.mytutorials.spring.batch.acl.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import com.mytutorials.domain.bookstore.entity.mapping.api.Author;
import com.mytutorials.domain.bookstore.entity.mapping.api.Book;

public class BookStoreServiceBookConverter implements
		Converter<Book, com.mytutorials.spring.batch.domain.Book> {

	@Autowired
	@Qualifier("bookStoreServiceAuthorConverter")
	private Converter<Author, com.mytutorials.spring.batch.domain.Author> converter;

	public com.mytutorials.spring.batch.domain.Book convert(Book source) {

		com.mytutorials.spring.batch.domain.Book book = null;

		if (source != null) {

			book = new com.mytutorials.spring.batch.domain.Book();
			book.setEdition(source.getEdition());
			book.setTitle(source.getTitle());
			book.setTotalNumberOfPages(source.getTotalNumberOfPages());

			if (source.getAuthors() != null && source.getAuthors().size() > 0) {

				for (Author author : source.getAuthors()) {

					com.mytutorials.spring.batch.domain.Author springBatchAuthor = converter
							.convert(author);
					springBatchAuthor.setBook(book);
					book.addAuthors(springBatchAuthor);
				}
			}
		}

		return book;
	}

}
