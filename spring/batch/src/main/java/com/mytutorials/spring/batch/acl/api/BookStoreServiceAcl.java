package com.mytutorials.spring.batch.acl.api;

import com.mytutorials.spring.batch.domain.Book;

public interface BookStoreServiceAcl {

	Book persist(Book book);
}
