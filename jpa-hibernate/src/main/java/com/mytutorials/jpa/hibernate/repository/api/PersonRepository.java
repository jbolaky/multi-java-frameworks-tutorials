package com.mytutorials.jpa.hibernate.repository.api;

import java.util.List;

import com.mytutorials.jpa.hibernate.entity.mapping.api.Person;

public interface PersonRepository {

	Person persist(Person person);

	Person find(Long id);

	List<Person> find(String firstName);

	Person merge(Long id, String newFirstName);

}
