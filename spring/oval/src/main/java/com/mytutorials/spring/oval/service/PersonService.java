package com.mytutorials.spring.oval.service;

import com.mytutorials.spring.oval.entity.Person;

public interface PersonService {

	void guardPerson(Person p);

	Person creatPerson(String name);

}
