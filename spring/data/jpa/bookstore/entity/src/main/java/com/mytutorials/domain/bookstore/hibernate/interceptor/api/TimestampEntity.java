package com.mytutorials.domain.bookstore.hibernate.interceptor.api;

import org.joda.time.LocalDateTime;

public interface TimestampEntity {

	public LocalDateTime getCreationDateTime();

	public LocalDateTime getLastModifiedDateTime();
}
