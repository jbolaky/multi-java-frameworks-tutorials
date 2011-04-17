package com.mytutorials.domain.bookstore.repository.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mytutorials.domain.bookstore.entity.mapping.api.Author;
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
@SuppressWarnings("unused")
public class DefaultBookStoreRepositoryIntegrationTest {

	@Autowired
	private BookStoreRepository bookStoreRepository;

	@Before
	public void populateUsername() {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				"Javaid", "Jav");
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@Test
	public void testSave() {
		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);

		Author author = createAuthor(defaultBook, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		Book book = bookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getCreationDateTime());
		assertThat(book.getCreationUsername(), is("Javaid"));
	}

	@Test
	public void testSaveAListOfBook() {

		List<DefaultBook> defaultBooks = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		DefaultBook defaultBook2 = createBook("Design Patterns", "2nd Edition",
				123);
		defaultBook2.addAuthor(author);
		defaultBook2.addAuthor(author2);

		DefaultBook defaultBook3 = createBook("Design Patterns", "2nd Edition",
				123);
		defaultBook3.addAuthor(author);
		defaultBook3.addAuthor(author2);

		defaultBooks = new ArrayList<DefaultBook>();
		defaultBooks.add(defaultBook);
		defaultBooks.add(defaultBook2);
		defaultBooks.add(defaultBook3);

		List<DefaultBook> defaultBooks2 = bookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
			assertThat(book.getCreationUsername(), is("Javaid"));
		}
	}

	@Test
	public void testSaveAndFindOne() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = bookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		book = bookStoreRepository.findOne(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getLastModifiedUsername(), is("Javaid"));
	}

	@Test
	public void testExist() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = bookStoreRepository.saveAndFlush(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getLastModifiedUsername(), is("Javaid"));

		assertTrue(bookStoreRepository.exists(book.getId()));
	}

	@Test
	public void testCount() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = bookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		assertTrue(bookStoreRepository.count() > 0);
	}

	@Test
	public void testDelete() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = bookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		bookStoreRepository.delete(defaultBook);

		book = bookStoreRepository.findOne(book.getId());
		assertNull(book);
	}

	@Test
	public void testFindAllWithSorting() {

		List<DefaultBook> defaultBooks = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				3);
		Author author = createAuthor(defaultBook, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		DefaultBook defaultBook2 = createBook("Design Patterns", "2nd Edition",
				2);
		defaultBook2.addAuthor(author);
		defaultBook2.addAuthor(author2);

		DefaultBook defaultBook3 = createBook("Design Patterns", "2nd Edition",
				10);
		defaultBook3.addAuthor(author);
		defaultBook3.addAuthor(author2);

		defaultBooks = new ArrayList<DefaultBook>();
		defaultBooks.add(defaultBook);
		defaultBooks.add(defaultBook2);
		defaultBooks.add(defaultBook3);

		List<DefaultBook> defaultBooks2 = bookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Order order = new Order(Direction.ASC, "totalNumberOfPages");
		Sort sort = new Sort(order);

		List<DefaultBook> books = bookStoreRepository.findAll(sort);

		assertNotNull(books);

		Integer totalNumPages = 0;
		for (Book defaultBook4 : books) {

			assertTrue(totalNumPages <= defaultBook4.getTotalNumberOfPages());
			totalNumPages = defaultBook4.getTotalNumberOfPages();
		}
	}

	@Test
	public void testFindAllWithPaging() {

		List<DefaultBook> defaultBooks = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				3);
		Author author = createAuthor(defaultBook, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		DefaultBook defaultBook2 = createBook("Design Patterns", "2nd Edition",
				2);
		defaultBook2.addAuthor(author);
		defaultBook2.addAuthor(author2);

		DefaultBook defaultBook3 = createBook("Design Patterns", "2nd Edition",
				10);
		defaultBook3.addAuthor(author);
		defaultBook3.addAuthor(author2);

		DefaultBook defaultBook4 = createBook("Design Patterns", "2nd Edition",
				14);
		defaultBook4.addAuthor(author);
		defaultBook4.addAuthor(author2);

		DefaultBook defaultBook5 = createBook("Design Patterns", "2nd Edition",
				510);
		defaultBook5.addAuthor(author);
		defaultBook5.addAuthor(author2);

		defaultBooks = new ArrayList<DefaultBook>();
		defaultBooks.add(defaultBook);
		defaultBooks.add(defaultBook2);
		defaultBooks.add(defaultBook3);
		defaultBooks.add(defaultBook4);
		defaultBooks.add(defaultBook5);

		List<DefaultBook> defaultBooks2 = bookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Pageable pageable = new PageRequest(1, 2, Direction.DESC,
				"totalNumberOfPages");

		Page<DefaultBook> book = bookStoreRepository.findAll(pageable);

		assertNotNull(book);

		assertTrue(book.getTotalPages() == 3);
		assertTrue(book.getNumber() == 1);
		assertTrue(book.getSize() == 2);

		List<DefaultBook> books = book.getContent();

		Integer totalNumPages = Integer.MAX_VALUE;
		for (Book defaultBook6 : books) {

			assertTrue(totalNumPages >= defaultBook6.getTotalNumberOfPages());
			totalNumPages = defaultBook6.getTotalNumberOfPages();
		}
	}

	@Test
	public void testSaveAndMergeAndFindOne() {

		Long bookId = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = bookStoreRepository.saveAndFlush(defaultBook);
		bookStoreRepository.flush();
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("Design Patterns", book.getTitle());

		bookId = book.getId();

		((DefaultBook) book).setTitle("EJB Pro 3");
		book = bookStoreRepository.saveAndFlush((DefaultBook) book);
		bookStoreRepository.flush();

		book = bookStoreRepository.findOne(bookId);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("EJB Pro 3", book.getTitle());
	}

	@Test
	public void testFindByTitle() {

		testSave();

		List<DefaultBook> books = bookStoreRepository.findByTitle("Design Patterns");

		assertThat("books should not be null", books, notNullValue());

		for (Book book : books) {
			assertThat(book.getTitle(), is("Design Patterns"));
		}
	}

	// @Test
	public void tesFindByCreationDateTime() {
		testSave();

		LocalDate from = new LocalDate().minusDays(1);
		LocalDate to = new LocalDate().plusDays(1);

		// List<Book> books = bookStoreRepository.findByCreationDateTime(from,
		// to);
		// assertFalse(books.isEmpty());
	}

	private DefaultBook createBook(String title, String edition,
			Integer totalNumPages) {

		DefaultBook defaultBook = new DefaultBook();
		defaultBook.setTitle(title);
		defaultBook.setEdition(edition);
		defaultBook.setTotalNumberOfPages(totalNumPages);

		return defaultBook;
	}

	private Author createAuthor(Book book, String firstName, String lastName) {

		DefaultAuthor defaultAuthor = new DefaultAuthor(book);
		defaultAuthor.setFirstName(firstName);
		defaultAuthor.setLastName(lastName);
		book.addAuthor(defaultAuthor);

		return defaultAuthor;
	}

}