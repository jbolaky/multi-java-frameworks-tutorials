package com.mytutorials.jpa.hibernate.entity.mapping.api;

import java.util.List;

public interface Person {

	Long getId();

	String getFirstName();

	String getLastName();

	Family getFamily();

	List<Job> getJobs();

	boolean add(Job job);

	boolean remove(Job job);

}
