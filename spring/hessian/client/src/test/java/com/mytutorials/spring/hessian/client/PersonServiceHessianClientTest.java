package com.mytutorials.spring.hessian.client;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorials.spring.hessian.common.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:default-hessian-client-context.xml" })
public class PersonServiceHessianClientTest {

	private static final Logger LOGGER = Logger
			.getLogger(PersonServiceHessianClientTest.class);

	@Resource(name = "personServiceHessianClient")
	private PersonServiceHessianClient personServiceHessianClient;

	@Test
	public void testAddAndGetMethods() {

		Person person = new Person("John", "Doe", Calendar.getInstance());
		person = personServiceHessianClient.add(person);
		person = personServiceHessianClient.get(person.getId());
		LOGGER.debug("Person = " + person);
		assertEquals("John", person.getFirstName());
	}
}
