package com.mytutorials.domain.bookstore.repository.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.mytutorials.domain.bookstore.repository.api.DataJPABookStoreRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/default-bookstore-entity-context.xml",
		"classpath:/default-bookstore-datasource-context.xml" })
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
@SuppressWarnings("unused")
public class DefaultBookStoreRepositoryIntegrationTest {

	@Autowired
	private DataJPABookStoreRepository dataJPABookStoreRepository;

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

		Book book = dataJPABookStoreRepository.save(defaultBook);
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

		List<DefaultBook> defaultBooks2 = dataJPABookStoreRepository
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

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		book = dataJPABookStoreRepository.findOne(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getLastModifiedUsername(), is("Javaid"));
	}

	@Test
	public void testExist() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = dataJPABookStoreRepository.saveAndFlush(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getLastModifiedUsername(), is("Javaid"));

		assertTrue(dataJPABookStoreRepository.exists(book.getId()));
	}

	@Test
	public void testCount() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		assertTrue(dataJPABookStoreRepository.count() > 0);
	}

	@Test
	public void testDelete() {

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		dataJPABookStoreRepository.delete(defaultBook);

		book = dataJPABookStoreRepository.findOne(book.getId());
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

		List<DefaultBook> defaultBooks2 = dataJPABookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Order order = new Order(Direction.ASC, "totalNumberOfPages");
		Sort sort = new Sort(order);

		List<DefaultBook> books = dataJPABookStoreRepository.findAll(sort);

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

		List<DefaultBook> defaultBooks2 = dataJPABookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Pageable pageable = new PageRequest(1, 2, Direction.DESC,
				"totalNumberOfPages");

		Page<DefaultBook> book = dataJPABookStoreRepository.findAll(pageable);

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

		Book book = dataJPABookStoreRepository.saveAndFlush(defaultBook);
		dataJPABookStoreRepository.flush();
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("Design Patterns", book.getTitle());

		bookId = book.getId();

		((DefaultBook) book).setTitle("EJB Pro 3");
		book = dataJPABookStoreRepository.saveAndFlush((DefaultBook) book);
		dataJPABookStoreRepository.flush();

		book = dataJPABookStoreRepository.findOne(bookId);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertEquals("EJB Pro 3", book.getTitle());
	}

	@Test
	public void testFindByTitle() {

		testSave();

		List<DefaultBook> books = dataJPABookStoreRepository
				.findByTitle("Design Patterns");

		assertThat("books should not be null", books, notNullValue());

		for (Book book : books) {
			assertThat(book.getTitle(), is("Design Patterns"));
		}
	}

	@Test
	public void findByAuthorsFirstName() {

		testSave();

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);

		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getCreationDateTime());
		assertThat(book.getCreationUsername(), is("Javaid"));

		defaultBook = createBook("Design Patterns", "2nd Edition", 123);

		author2 = createAuthor(defaultBook, "Grady", "Holler");

		book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getCreationDateTime());
		assertThat(book.getCreationUsername(), is("Javaid"));

		List<DefaultBook> books = dataJPABookStoreRepository
				.findByAuthorsFirstName("Grady");

		assertThat("books should not be null", books, notNullValue());
		assertThat("books size should be 1", books.size(), is(2));

		for (Book book1 : books) {
			assertThat(
					isFirstNamePresentInAuthors("Grady", book1.getAuthors()),
					equalTo(true));
		}
	}

	@Test
	public void testFindByAuthorsFirstNameAndPaging() {

		List<DefaultBook> defaultBooks = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				3);

		DefaultBook defaultBook2 = createBook("Design Patterns", "2nd Edition",
				2);

		DefaultBook defaultBook3 = createBook("Design Patterns", "2nd Edition",
				10);

		DefaultBook defaultBook4 = createBook("Design Patterns", "2nd Edition",
				14);

		DefaultBook defaultBook5 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook6 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook7 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook8 = createBook("Design Patterns", "2nd Edition",
				510);

		Author author = createAuthor(defaultBook2, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook2, "Jurgen", "Holler");
		Author author3 = createAuthor(defaultBook3, "Douglas", "Adams");
		Author author4 = createAuthor(defaultBook3, "Grady", "Booch");
		Author author5 = createAuthor(defaultBook4, "Grady", "Booch");
		Author author6 = createAuthor(defaultBook4, "Jurgen", "Holler");
		Author author7 = createAuthor(defaultBook5, "Grady", "Booch");
		Author author8 = createAuthor(defaultBook5, "Wiliam", "Shakespeares");
		Author author9 = createAuthor(defaultBook6, "Grady", "Booch");
		Author author10 = createAuthor(defaultBook6, "Jurgen", "Holler");
		Author author11 = createAuthor(defaultBook7, "Douglas", "Adams");
		Author author12 = createAuthor(defaultBook7, "Grady", "Booch");
		Author author13 = createAuthor(defaultBook8, "Grady", "Booch");
		Author author14 = createAuthor(defaultBook8, "Jurgen", "Holler");

		defaultBook2.addAuthor(author);
		defaultBook2.addAuthor(author2);

		defaultBook3.addAuthor(author3);
		defaultBook3.addAuthor(author4);

		defaultBook4.addAuthor(author5);
		defaultBook4.addAuthor(author6);

		defaultBook5.addAuthor(author7);
		defaultBook5.addAuthor(author8);

		defaultBook6.addAuthor(author9);
		defaultBook6.addAuthor(author10);

		defaultBook7.addAuthor(author11);
		defaultBook7.addAuthor(author12);

		defaultBook8.addAuthor(author13);
		defaultBook8.addAuthor(author14);

		defaultBooks = new ArrayList<DefaultBook>();
		defaultBooks.add(defaultBook);
		defaultBooks.add(defaultBook2);
		defaultBooks.add(defaultBook3);
		defaultBooks.add(defaultBook4);
		defaultBooks.add(defaultBook5);
		defaultBooks.add(defaultBook6);
		defaultBooks.add(defaultBook7);
		defaultBooks.add(defaultBook8);

		List<DefaultBook> defaultBooks2 = dataJPABookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Pageable pageable = new PageRequest(1, 2, Direction.DESC,
				"totalNumberOfPages");

		Page<DefaultBook> book = dataJPABookStoreRepository
				.findByAuthorsFirstName("Grady", pageable);

		assertNotNull(book);

		assertTrue(book.getTotalPages() == 4);
		assertTrue(book.getNumber() == 1);
		assertTrue(book.getSize() == 2);

		List<DefaultBook> books = book.getContent();

		Integer totalNumPages = Integer.MAX_VALUE;
		for (Book defaultBook9 : books) {

			assertTrue(totalNumPages >= defaultBook9.getTotalNumberOfPages());
			assertThat(
					isFirstNamePresentInAuthors("Grady",
							defaultBook9.getAuthors()), equalTo(true));
			totalNumPages = defaultBook9.getTotalNumberOfPages();
		}
	}

	@Test
	public void testFindByAuthorsFirstNameWithSorting() {

		List<DefaultBook> defaultBooks = null;

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				3);

		DefaultBook defaultBook2 = createBook("Design Patterns", "2nd Edition",
				2);

		DefaultBook defaultBook3 = createBook("Design Patterns", "2nd Edition",
				10);

		DefaultBook defaultBook4 = createBook("Design Patterns", "2nd Edition",
				14);

		DefaultBook defaultBook5 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook6 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook7 = createBook("Design Patterns", "2nd Edition",
				510);

		DefaultBook defaultBook8 = createBook("Design Patterns", "2nd Edition",
				510);

		Author author = createAuthor(defaultBook2, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook2, "Jurgen", "Holler");
		Author author3 = createAuthor(defaultBook3, "Douglas", "Adams");
		Author author4 = createAuthor(defaultBook3, "Grady", "Booch");
		Author author5 = createAuthor(defaultBook4, "Grady", "Booch");
		Author author6 = createAuthor(defaultBook4, "Jurgen", "Holler");
		Author author7 = createAuthor(defaultBook5, "Grady", "Booch");
		Author author8 = createAuthor(defaultBook5, "Wiliam", "Shakespeares");
		Author author9 = createAuthor(defaultBook6, "Grady", "Booch");
		Author author10 = createAuthor(defaultBook6, "Jurgen", "Holler");
		Author author11 = createAuthor(defaultBook7, "Douglas", "Adams");
		Author author12 = createAuthor(defaultBook7, "Grady", "Booch");
		Author author13 = createAuthor(defaultBook8, "Grady", "Booch");
		Author author14 = createAuthor(defaultBook8, "Jurgen", "Holler");

		defaultBook2.addAuthor(author);
		defaultBook2.addAuthor(author2);

		defaultBook3.addAuthor(author3);
		defaultBook3.addAuthor(author4);

		defaultBook4.addAuthor(author5);
		defaultBook4.addAuthor(author6);

		defaultBook5.addAuthor(author7);
		defaultBook5.addAuthor(author8);

		defaultBook6.addAuthor(author9);
		defaultBook6.addAuthor(author10);

		defaultBook7.addAuthor(author11);
		defaultBook7.addAuthor(author12);

		defaultBook8.addAuthor(author13);
		defaultBook8.addAuthor(author14);

		defaultBooks = new ArrayList<DefaultBook>();
		defaultBooks.add(defaultBook);
		defaultBooks.add(defaultBook2);
		defaultBooks.add(defaultBook3);
		defaultBooks.add(defaultBook4);
		defaultBooks.add(defaultBook5);
		defaultBooks.add(defaultBook6);
		defaultBooks.add(defaultBook7);
		defaultBooks.add(defaultBook8);

		List<DefaultBook> defaultBooks2 = dataJPABookStoreRepository
				.save(defaultBooks);

		for (Book book : defaultBooks2) {
			assertNotNull(book);
			assertNotNull(book.getId());
			assertNotNull(book.getCreationDateTime());
		}

		Sort sort = new Sort(Direction.DESC, "totalNumberOfPages");

		List<DefaultBook> books = dataJPABookStoreRepository
				.findByAuthorsFirstName("Grady", sort);

		assertNotNull(books);

		assertTrue(books.size() == 7);

		Integer totalNumPages = Integer.MAX_VALUE;
		for (Book defaultBook9 : books) {

			assertTrue(totalNumPages >= defaultBook9.getTotalNumberOfPages());
			assertThat(
					isFirstNamePresentInAuthors("Grady",
							defaultBook9.getAuthors()), equalTo(true));
			totalNumPages = defaultBook9.getTotalNumberOfPages();
		}
	}

	@Test
	public void testFindByEdition() {

		testSave();
		testSave();

		final String edition = "2nd Edition";

		List<DefaultBook> defaultBooks = dataJPABookStoreRepository
				.findByEdition(edition);

		assertThat(defaultBooks, is(notNullValue()));
		assertThat(defaultBooks.size(), is(2));

		for (DefaultBook defaultBook : defaultBooks) {

			assertThat(defaultBook.getEdition(), is(edition));
		}
	}

	@Test
	public void testFindByEditionAndTitleAndTotalNumberOfPages() {

		final String edition = "3rd";
		final String title = "Lion King";
		final Integer totalNumPages = 1234;

		testSave();
		testSave();
		testSave();

		DefaultBook defaultBook = createBook(title, edition, totalNumPages);

		Author author = createAuthor(defaultBook, "Grady", "Booch");
		Author author2 = createAuthor(defaultBook, "Jurgen", "Holler");

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());
		assertNotNull(book.getCreationDateTime());
		assertThat(book.getCreationUsername(), is("Javaid"));

		List<DefaultBook> defaultBooks = dataJPABookStoreRepository
				.findByEditionAndTitleAndTotalNumberOfPages(edition, title,
						totalNumPages);

		assertThat(defaultBooks, is(notNullValue()));
		assertThat(defaultBooks.size(), is(1));

		for (DefaultBook defaultBook1 : defaultBooks) {

			assertThat(defaultBook1.getEdition(), is(edition));
			assertThat(defaultBook1.getTitle(), is(title));
			assertThat(defaultBook1.getTotalNumberOfPages(), is(totalNumPages));
		}
	}

	@Test
	public void testUpdateTitleById() {

		final String newTitle = "newTitle";

		DefaultBook defaultBook = createBook("Design Patterns", "2nd Edition",
				123);
		Author author = createAuthor(defaultBook, "Grady", "Booch");

		Book book = dataJPABookStoreRepository.save(defaultBook);
		assertNotNull(book);
		assertNotNull(book.getId());

		book = dataJPABookStoreRepository.findOne(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getLastModifiedUsername(), is("Javaid"));

		dataJPABookStoreRepository.updateTitleById(newTitle, book.getId());

		book = dataJPABookStoreRepository.findOne(book.getId());
		assertNotNull(book);
		assertNotNull(book.getId());
		assertThat(book.getTitle(), is(newTitle));
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

	private boolean isFirstNamePresentInAuthors(String firstName,
			Set<Author> authors) {

		boolean isPresent = false;

		if (firstName != null && authors != null && !authors.isEmpty()) {

			for (Author author : authors) {

				if (firstName.equals(author.getFirstName())) {

					isPresent = true;
					break;
				}
			}
		}

		return isPresent;
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