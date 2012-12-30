package com.mytutorials.spring.batch.acl.converter;

import org.springframework.core.convert.converter.Converter;

import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultAuthor;
import com.mytutorials.spring.batch.domain.Author;

public class SpringBatchAuthorConverter
		implements
		Converter<Author, com.mytutorials.domain.bookstore.entity.mapping.api.Author> {

	public com.mytutorials.domain.bookstore.entity.mapping.api.Author convert(
			Author source) {
		
		DefaultAuthor defaultAuthor = null;
		
		if(source!=null){
			
			defaultAuthor = new DefaultAuthor(null);
			defaultAuthor.setFirstName(source.getFirstName());
			defaultAuthor.setLastName(source.getLastName());
		}
		
		return defaultAuthor;
	}

	
}
