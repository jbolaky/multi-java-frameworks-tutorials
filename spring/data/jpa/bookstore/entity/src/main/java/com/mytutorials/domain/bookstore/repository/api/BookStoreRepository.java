package com.mytutorials.domain.bookstore.repository.api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;

public interface BookStoreRepository extends JpaRepository<DefaultBook, Long> {

	List<DefaultBook> findByTitle(String bookTitle);
}
