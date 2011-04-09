package com.mytutorials.spring.oval.service;

import static org.junit.Assert.fail;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorials.spring.oval.entity.Person;
import com.mytutorials.spring.oval.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:default-oval-spring-context.xml" })
public class DefaultPersonServiceTest {

	@Autowired
	private PersonService personService;

	@Test
	public void guardPerson() {
		Person p = new Person("Invasor", "Zim");
		personService.guardPerson(p);
	}

	@Test(expected = ConstraintsViolatedException.class)
	public void guardPerson_personaNull() {
		personService.guardPerson(null);
		fail("Should have been a validation exception");
	}
	
}
