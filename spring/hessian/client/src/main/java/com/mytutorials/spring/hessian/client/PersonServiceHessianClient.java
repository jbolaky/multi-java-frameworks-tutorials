package com.mytutorials.spring.hessian.client;

import javax.annotation.Resource;

import com.mytutorials.spring.hessian.common.IPersonService;
import com.mytutorials.spring.hessian.common.Person;

public class PersonServiceHessianClient {

	@Resource(name = "personService")
	private IPersonService personService = null;

	public Person get(Integer id) {
		return personService.get(id);
	}

	public Person add(Person person) {
		return personService.add(person);
	}

	public void delete(Integer personId) {
		personService.delete(personId);
	}

	public void update(Person person) {
		personService.update(person);
	}

}
