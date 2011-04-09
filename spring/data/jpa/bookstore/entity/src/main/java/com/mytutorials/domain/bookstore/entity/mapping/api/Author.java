package com.mytutorials.domain.bookstore.entity.mapping.api;

import java.io.Serializable;

public interface Author extends TimestampUsername, Serializable {

	Long getId();

	String getFirstName();

	String getLastName();

	Book getBook();

}
