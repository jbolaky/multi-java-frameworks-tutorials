package com.mytutorials.spring.batch.fieldsetmapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.mytutorials.spring.batch.domain.Author;

public class AuthorFieldSetMapper implements FieldSetMapper<Author>{

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorFieldSetMapper.class);
	
	public Author mapFieldSet(FieldSet fieldSet) throws BindException {
		
		Author author = null;
		
		if(fieldSet==null){
			
			return author;
		}
		
		author = new Author();
		author.setFirstName(fieldSet.readString("firstName"));
		author.setLastName(fieldSet.readString("lastName"));
		author.setBookTitle(fieldSet.readString("bookTitle"));
		
		if(LOGGER.isDebugEnabled()){
			
			LOGGER.debug(author.toString());
		}
		
		return author;
	}

}
