package com.mytutorial.spring.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mytutorial.spring.cache.api.BookStoreService;
import com.mytutorials.domain.bookstore.entity.mapping.api.Book;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultAuthor;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/default-spring-cache-context.xml",
		"classpath:/default-spring-cache-datasource-context.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager")
public class DefaultBookStoreServiceIntegrationTest {

	@Autowired
	private BookStoreService bookStoreService;

	@Test
	public void testPersist() {
		DefaultBook defaultBook = new DefaultBook();
		defaultBook.setCreationUsername("JavaidB");
		defaultBook.setLastModifiedUsername("JavaidB");
		defaultBook.setTitle("Design Patterns");
		defaultBook.setEdition("2nd Edition");
		defaultBook.setTotalNumberOfPages(123);

		DefaultAuthor defaultAuthor = new DefaultAuthor(defaultBook);
		defaultAuthor.setFirstName("Grady");
		defaultAuthor.setLastName("Booch");
		defaultAuthor.setCreationUsername("JavaidB");
		defaultAuthor.setLastModifiedUsername("JavaidB");
		defaultBook.addAuthor(defaultAuthor);

		DefaultAuthor defaultAuthor2 = new DefaultAuthor(defaultBook);
		defaultAuthor2.setFirstName("Jurgen");
		defaultAuthor2.setLastName("Holler");
		defaultAuthor2.setCreationUsername("JavaidB");
		defaultAuthor2.setLastModifiedUsername("JavaidB");
		defaultBook.addAuthor(defaultAuthor2);

		Book book = bookStoreService.persist(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
	}

	@Test
	public void testPersistAndFindById() {
		DefaultBook defaultBook = new DefaultBook();
		defaultBook.setCreationUsername("JavaidB");
		defaultBook.setLastModifiedUsername("JavaidB");
		defaultBook.setTitle("Design Patterns");
		defaultBook.setEdition("2nd Edition");
		defaultBook.setTotalNumberOfPages(123);

		DefaultAuthor defaultAuthor = new DefaultAuthor(defaultBook);
		defaultAuthor.setFirstName("Grady");
		defaultAuthor.setLastName("Booch");
		defaultAuthor.setCreationUsername("JavaidB");
		defaultAuthor.setLastModifiedUsername("JavaidB");

		defaultBook.addAuthor(defaultAuthor);

		Book book = bookStoreService.persist(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		book = bookStoreService.findById(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
	}

	@Test
	public void testPersistAndMergeAndFindById() {
		DefaultBook defaultBook = new DefaultBook();
		defaultBook.setCreationUsername("JavaidB");
		defaultBook.setLastModifiedUsername("JavaidB");
		defaultBook.setTitle("Design Patterns");
		defaultBook.setEdition("2nd Edition");
		defaultBook.setTotalNumberOfPages(123);

		DefaultAuthor defaultAuthor = new DefaultAuthor(defaultBook);
		defaultAuthor.setFirstName("Grady");
		defaultAuthor.setLastName("Booch");
		defaultAuthor.setCreationUsername("JavaidB");
		defaultAuthor.setLastModifiedUsername("JavaidB");

		defaultBook.addAuthor(defaultAuthor);

		Book book = bookStoreService.persist(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("Design Patterns", book.getTitle());

		((DefaultBook) book).setTitle("EJB Pro 3");
		book = bookStoreService.merge(book);

		book = bookStoreService.findById(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("EJB Pro 3", book.getTitle());
	}

	@Test
	public void tesFindByCreationDateTime() {
		testPersist();

		LocalDate from = new LocalDate().minusDays(1);
		LocalDate to = new LocalDate().plusDays(1);

		List<Book> books = bookStoreService
				.findByCreationDateTimeWithOutCacheable(from, to);
		assertFalse(books.isEmpty());
	}

	@Test
	@Ignore
	public void testNotNullForFindById() {

		bookStoreService.findById(null);

	}

	@Test
	@SuppressWarnings("unused")
	public void testCaching() {

		LocalDate from = new LocalDate().minusDays(1);
		LocalDate to = new LocalDate().plusDays(1);

		LocalTime before = new LocalTime();
		LocalTime after = new LocalTime();
		int timeTakenBeforeCachedInMilliSeconds = 0;
		int timeTakenWithCacheInMilliSeconds = 0;

		Date date = new Date();

		testPersist();

		before = new LocalTime();
		// Sql queries should be executed
		List<Book> books = bookStoreService
				.findByCreationDateTimeWithCacheable(from, to);
		after = new LocalTime();
		timeTakenBeforeCachedInMilliSeconds = after.getMillisOfDay()
				- before.getMillisOfDay();
		assertNotNull(books);
		assertTrue(books.size() > 0);

		before = new LocalTime();
		// Sql queries should not be executed
		List<Book> books2 = bookStoreService
				.findByCreationDateTimeWithCacheable(from, to);
		after = new LocalTime();
		timeTakenWithCacheInMilliSeconds = after.getMillisOfDay()
				- before.getMillisOfDay();
		assertNotNull(books2);
		assertTrue(books2.size() > 0);

		System.out.println("timeTakenBeforeCachedInMilliSeconds is "
				+ timeTakenBeforeCachedInMilliSeconds);
		System.out.println("timeTakenWithCacheInMilliSeconds is :"
				+ timeTakenWithCacheInMilliSeconds);

		assertTrue(timeTakenWithCacheInMilliSeconds < timeTakenBeforeCachedInMilliSeconds);
	}

	private Book createBook(DefaultBook defaultBook) {

		defaultBook.setCreationUsername("JavaidB");
		defaultBook.setLastModifiedUsername("JavaidB");
		defaultBook.setTitle("Design Patterns");
		defaultBook.setEdition("2nd Edition");
		defaultBook.setTotalNumberOfPages(123);

		DefaultAuthor defaultAuthor = new DefaultAuthor(defaultBook);
		defaultAuthor.setFirstName("Grady");
		defaultAuthor.setLastName("Booch");
		defaultAuthor.setCreationUsername("JavaidB");
		defaultAuthor.setLastModifiedUsername("JavaidB");
		defaultBook.addAuthor(defaultAuthor);

		DefaultAuthor defaultAuthor2 = new DefaultAuthor(defaultBook);
		defaultAuthor2.setFirstName("Jurgen");
		defaultAuthor2.setLastName("Holler");
		defaultAuthor2.setCreationUsername("JavaidB");
		defaultAuthor2.setLastModifiedUsername("JavaidB");
		defaultBook.addAuthor(defaultAuthor2);

		return defaultBook;
	}
}
