package com.mytutorials.spring.batch.itemprocessor;

import org.springframework.batch.item.ItemProcessor;

import com.mytutorials.spring.batch.domain.Author;

public class AuthorItemProcessor implements ItemProcessor<Author, Author> {

	public Author process(Author item) throws Exception {

		return item != null && item.getLastName() != null
				&& item.getLastName().length() != 0 ? item : null;
	}

}
