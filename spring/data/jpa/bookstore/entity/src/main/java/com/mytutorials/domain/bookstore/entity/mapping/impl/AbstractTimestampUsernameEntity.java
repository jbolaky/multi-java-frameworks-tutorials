package com.mytutorials.domain.bookstore.entity.mapping.impl;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.mytutorials.domain.bookstore.entity.mapping.api.TimestampUsername;
import com.mytutorials.domain.bookstore.hibernate.interceptor.api.TimestampEntity;
import com.mytutorials.domain.bookstore.hibernate.interceptor.api.UsernameEntity;

@MappedSuperclass
public abstract class AbstractTimestampUsernameEntity implements
		TimestampUsername, TimestampEntity, UsernameEntity {

	private static final long serialVersionUID = 1836445425143824225L;

	@Version
	@Column(name = "VERSION")
	private Timestamp version;

	@Column(name = "CREATION_DATE_TIME")
	@Type(type = "hibernate_persistentDateTime")
	private DateTime creationDateTime;

	@Column(name = "CREATION_USERNAME")
	private String creationUsername;

	@Column(name = "LAST_MODIFIED_DATE_TIME")
	@Type(type = "hibernate_persistentDateTime")
	private DateTime lastModifiedDateTime;

	@Column(name = "LAST_MODIFIED_USERNAME")
	private String lastModifiedUsername;

	public Timestamp getVersion() {
		return version;
	}

	public DateTime getCreationDateTime() {
		return creationDateTime;
	}

	public String getCreationUsername() {
		return creationUsername;
	}

	public DateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public String getLastModifiedUsername() {
		return lastModifiedUsername;
	}

	public void setCreationUsername(String creationUsername) {
		this.creationUsername = creationUsername;
	}

	public void setLastModifiedUsername(String lastModifiedUsername) {
		this.lastModifiedUsername = lastModifiedUsername;
	}

}
