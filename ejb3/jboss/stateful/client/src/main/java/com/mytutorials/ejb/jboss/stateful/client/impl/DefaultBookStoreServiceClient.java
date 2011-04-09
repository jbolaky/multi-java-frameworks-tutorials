package com.mytutorials.ejb.jboss.stateful.client.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mytutorials.ejb.jboss.stateful.client.api.BookStoreServiceClient;
import com.mytutorials.ejb3.jboss.stateful.api.BookStoreService;
import com.mytutorials.ejb3.jboss.stateful.vo.Book;

public class DefaultBookStoreServiceClient implements BookStoreServiceClient {

	private BookStoreService bookStoreService;
	private String jndiPropertiesFileName = "jndi.properties";
	private String lookUpName = "ejb3.jboss.stateful.server.ear-1.0-SNAPSHOT/DefaultBookStoreService/remote";

	public DefaultBookStoreServiceClient() throws FileNotFoundException, IOException, NamingException {
		super();
		Properties props = new Properties();
		props.load(new FileInputStream(jndiPropertiesFileName));
		InitialContext ctx = new InitialContext(props);
		bookStoreService = (BookStoreService) ctx.lookup(lookUpName);

	}

	public Book addBook(Book book) {
		return bookStoreService.addBook(book);
	}

	public List<Book> getAllBooks() {
		return bookStoreService.getAllBooks();
	}

}
