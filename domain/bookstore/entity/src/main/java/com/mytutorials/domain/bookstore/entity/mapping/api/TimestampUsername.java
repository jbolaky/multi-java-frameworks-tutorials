package com.mytutorials.domain.bookstore.entity.mapping.api;

import java.io.Serializable;
import java.sql.Timestamp;

import org.joda.time.LocalDateTime;

public interface TimestampUsername extends Serializable {

	Timestamp getVersion();

	LocalDateTime getCreationDateTime();

	String getCreationUsername();

	LocalDateTime getLastModifiedDateTime();

	String getLastModifiedUsername();
}
