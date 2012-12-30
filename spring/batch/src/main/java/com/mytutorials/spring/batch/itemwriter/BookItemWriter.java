package com.mytutorials.spring.batch.itemwriter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.mytutorials.spring.batch.acl.api.BookStoreServiceAcl;
import com.mytutorials.spring.batch.domain.Author;
import com.mytutorials.spring.batch.domain.Book;

public class BookItemWriter implements ItemWriter<Book> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BookItemWriter.class);

	@Autowired
	private BookStoreServiceAcl bookStoreServiceAcl;

	public void write(List<? extends Book> books) throws Exception {

		List<Author> authors = AuthorItemWriter.authors;

		if (books != null && books.size() > 0) {

			for (Book book : books) {

				if (book != null && book.getTitle() != null) {

					for (Author author : authors) {

						if (author != null
								&& author.getBookTitle() != null
								&& author
										.getBookTitle()
										.trim()
										.equalsIgnoreCase(
												book.getTitle().trim())) {

							book.addAuthors(author);
						}
					}
				}
			}
		}

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(books.toString());
		}

		for (Book book : books) {

			@SuppressWarnings("unused")
			Book book2 = bookStoreServiceAcl.persist(book);
		}
	}

}
