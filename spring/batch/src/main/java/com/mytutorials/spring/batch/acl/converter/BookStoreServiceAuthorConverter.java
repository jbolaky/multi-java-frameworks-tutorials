package com.mytutorials.spring.batch.acl.converter;

import org.springframework.core.convert.converter.Converter;

import com.mytutorials.domain.bookstore.entity.mapping.api.Author;

public class BookStoreServiceAuthorConverter implements Converter<Author, com.mytutorials.spring.batch.domain.Author>{

	public com.mytutorials.spring.batch.domain.Author convert(Author source) {
		
		com.mytutorials.spring.batch.domain.Author author = null;
		
		if(source!=null){
			
			author = new com.mytutorials.spring.batch.domain.Author();
			author.setFirstName(source.getFirstName());
			author.setLastName(source.getLastName());
		}
		
		return author;
	}

	
}
