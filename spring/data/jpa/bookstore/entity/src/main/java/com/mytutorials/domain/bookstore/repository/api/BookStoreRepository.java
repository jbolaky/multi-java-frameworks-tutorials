package com.mytutorials.domain.bookstore.repository.api;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytutorials.domain.bookstore.entity.mapping.impl.DefaultBook;

public interface BookStoreRepository extends JpaRepository<DefaultBook, Long> {

}
