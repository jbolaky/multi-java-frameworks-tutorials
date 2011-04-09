package com.mytutorials.spring.oval.service;

import net.sf.oval.constraint.NotNull;

import com.mytutorials.spring.oval.entity.Person;

public class DefaultPersonService implements PersonService {

	@Override
	public void guardPerson(@NotNull Person p) {
		System.out.println("Saving person:" + p);
	}

	@Override
	public Person creatPerson(String name) {

		if (name == null) {
			return null;
		}

		return new Person(name, "Name not specified");
	}

}
