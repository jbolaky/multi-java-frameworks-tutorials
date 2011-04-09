package com.mytutorials.ejb.jboss.stateless.client.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.Assert.*;
import javax.naming.NamingException;

import org.junit.Ignore;
import org.junit.Test;

import com.mytutorials.ejb.jboss.stateless.client.api.BookStoreServiceClient;
import com.mytutorials.ejb3.jboss.stateless.vo.Book;

@Ignore
public class DefaultBookStoreServiceClientIntegrationTest {

	@Test
	public void testAddBookAndGetAllBooks() throws FileNotFoundException, IOException, NamingException {

		BookStoreServiceClient bookStoreServiceClient = new DefaultBookStoreServiceClient();

		Book book = new Book("Pro EJB3");
		Book book2 = new Book("CSS - The definitive guide");

		assertEquals(book, bookStoreServiceClient.addBook(book));
		assertEquals(book2, bookStoreServiceClient.addBook(book2));
		assertTrue(bookStoreServiceClient.getAllBooks().size() == 2);

		BookStoreServiceClient bookStoreServiceClient2 = new DefaultBookStoreServiceClient();
		Book book3 = new Book("Spring Recipes");
		Book book4 = new Book("Hibernate In Action");

		assertEquals(book3, bookStoreServiceClient2.addBook(book3));
		assertEquals(book4, bookStoreServiceClient2.addBook(book4));
		assertTrue(bookStoreServiceClient2.getAllBooks().size() == 4);
	}
}
