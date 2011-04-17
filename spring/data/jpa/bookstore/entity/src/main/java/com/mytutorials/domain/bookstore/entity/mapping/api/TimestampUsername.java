package com.mytutorials.domain.bookstore.entity.mapping.api;

import java.io.Serializable;
import java.sql.Timestamp;

import org.joda.time.DateTime;

public interface TimestampUsername extends Serializable {

	Timestamp getVersion();

	DateTime getCreationDateTime();

	String getCreationUsername();

	DateTime getLastModifiedDateTime();

	String getLastModifiedUsername();
}
