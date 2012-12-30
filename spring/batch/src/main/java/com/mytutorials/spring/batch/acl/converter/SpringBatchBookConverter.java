package com.mytutorials.spring.batch.acl.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;

import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultAuthor;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;
import com.mytutorials.spring.batch.domain.Author;
import com.mytutorials.spring.batch.domain.Book;

public class SpringBatchBookConverter
		implements
		Converter<Book, com.mytutorials.domain.bookstore.entity.mapping.api.Book> {

	@Autowired
	@Qualifier("springBatchAuthorConverter")
	private Converter<Author, com.mytutorials.domain.bookstore.entity.mapping.api.Author> converter;

	public com.mytutorials.domain.bookstore.entity.mapping.api.Book convert(
			Book book) {

		DefaultBook defaultBook = null;

		if (book != null) {

			defaultBook = new DefaultBook();
			defaultBook.setTitle(book.getTitle());
			defaultBook.setEdition(book.getEdition());
			defaultBook.setTotalNumberOfPages(book.getTotalNumberOfPages());

			if (book.getAuthors() != null && book.getAuthors().size() > 0) {

				for (Author author : book.getAuthors()) {

					if (author != null) {

						DefaultAuthor bookStoreServiceAuthor = (DefaultAuthor) converter
								.convert(author);
						bookStoreServiceAuthor.setBook(defaultBook);
						defaultBook.addAuthor(bookStoreServiceAuthor);
					}
				}
			}
		}

		return defaultBook;
	}

}
