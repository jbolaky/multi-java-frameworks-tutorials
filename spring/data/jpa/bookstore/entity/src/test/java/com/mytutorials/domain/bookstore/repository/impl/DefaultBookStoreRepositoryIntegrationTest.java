package com.mytutorials.domain.bookstore.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultAuthor;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;
import com.mytutorials.domain.bookstore.repository.api.BookStoreRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/default-bookstore-entity-context.xml",
		"classpath:/default-bookstore-datasource-context.xml" })
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
public class DefaultBookStoreRepositoryIntegrationTest {

	@Resource(name = "default_BookStore_Repository")
	private BookStoreRepository bookStoreRepository;

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

		Book book = bookStoreRepository.persist(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getCreationDateTime());
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

		Book book = bookStoreRepository.persist(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		book = bookStoreRepository.findById(book.getId());
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

		Book book = bookStoreRepository.persist(defaultBook);
		bookStoreRepository.flush();
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("Design Patterns", book.getTitle());

		((DefaultBook) book).setTitle("EJB Pro 3");
		book = bookStoreRepository.merge(book);
		bookStoreRepository.flush();

		book = bookStoreRepository.findById(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("EJB Pro 3", book.getTitle());
	}

	@Test
	public void tesFindByCreationDateTime() {
		testPersist();

		LocalDate from = new LocalDate().minusDays(1);
		LocalDate to = new LocalDate().plusDays(1);

		List<Book> books = bookStoreRepository.findByCreationDateTime(from, to);
		assertFalse(books.isEmpty());
	}

}
