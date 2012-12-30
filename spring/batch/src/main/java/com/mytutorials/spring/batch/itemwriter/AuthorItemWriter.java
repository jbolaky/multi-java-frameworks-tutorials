package com.mytutorials.spring.batch.itemwriter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.mytutorials.spring.batch.domain.Author;

public class AuthorItemWriter implements ItemWriter<Author> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthorItemWriter.class);

	public static List<Author> authors = new ArrayList<Author>();

	@SuppressWarnings("unchecked")
	public void write(List<? extends Author> items) throws Exception {

		authors = ListUtils.sum(authors, items);

		if (LOGGER.isDebugEnabled()) {

			LOGGER.debug(authors.toString());
		}
	}

}
