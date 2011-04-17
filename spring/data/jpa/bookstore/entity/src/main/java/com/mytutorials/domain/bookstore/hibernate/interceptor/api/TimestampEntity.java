package com.mytutorials.domain.bookstore.hibernate.interceptor.api;

import org.joda.time.DateTime;

public interface TimestampEntity {

	public DateTime getCreationDateTime();

	public DateTime getLastModifiedDateTime();
}
