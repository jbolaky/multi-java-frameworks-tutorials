package com.mytutorials.spring.batch.fieldsetmapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mytutorials.spring.batch.domain.Book;

public class BookFieldSetMapper implements FieldSetMapper<Book> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BookFieldSetMapper.class);

	public Book mapFieldSet(FieldSet fieldSet) throws BindException {

		Book book = null;

		if (fieldSet == null) {

			return book;
		}

		book = new Book();
		book.setTitle(removePrefix(fieldSet.readString("title")));
		book.setTotalNumberOfPages(Integer.parseInt(fieldSet
				.readString("totalNumberOfPages")));
		book.setEdition(fieldSet.readString("edition"));

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(book.toString());
		}

		return book;
	}

	private String removePrefix(String token) {
		String[] tokens = token.split(";");
		if (tokens.length == 2) {
			return tokens[1];
		}
		return token;
	}
}
