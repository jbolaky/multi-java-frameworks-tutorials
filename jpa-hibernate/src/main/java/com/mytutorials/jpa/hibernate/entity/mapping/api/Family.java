package com.mytutorials.jpa.hibernate.entity.mapping.api;

import java.util.Set;

public interface Family {

	Long getId();

	String getDescription();

	Set<Person> getMembers();

	boolean add(Person member);

	boolean remove(Person member);

}
