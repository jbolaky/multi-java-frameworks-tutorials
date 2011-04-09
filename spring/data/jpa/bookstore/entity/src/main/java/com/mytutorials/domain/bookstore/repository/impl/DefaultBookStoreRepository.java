package com.mytutorials.domain.bookstore.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Repository;

import com.mytutorials.domain.bookstore.entity.mapping.api.Book;
import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;
import com.mytutorials.domain.bookstore.repository.api.BookStoreRepository;

@Repository(value = "default_BookStore_Repository")
public class DefaultBookStoreRepository implements BookStoreRepository {

	@PersistenceContext(unitName = "bookstore_persistence_unit_name")
	private EntityManager entityManager;

	public Book persist(Book book) {
		entityManager.persist(book);
		return book;
	}

	public Book findById(Long id) {
		return entityManager.find(DefaultBook.class, id);
	}

	public Book merge(Book book) {
		return entityManager.merge(book);
	}

	public void flush() {
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Book> findByCreationDateTime(LocalDate from, LocalDate to) {

		List<Book> books = null;

		Session session = (Session) entityManager.getDelegate();
		Criteria criteria = session.createCriteria(DefaultBook.class);

		criteria.add(Restrictions.le("creationDateTime",
				to.toLocalDateTime(new LocalTime(DateTimeZone.UTC))));
		criteria.add(Restrictions.ge("creationDateTime",
				from.toLocalDateTime(new LocalTime(DateTimeZone.UTC))));

		books = criteria.list();

		return books;
	}
}
