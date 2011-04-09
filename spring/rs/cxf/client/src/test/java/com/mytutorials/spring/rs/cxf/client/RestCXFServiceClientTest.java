package com.mytutorials.spring.rs.cxf.client;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorials.spring.rs.cxf.entity.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:default-rs-cxf-client-context.xml",
		"classpath:default-rs-cxf-client-url-config-context.xml" })
public class RestCXFServiceClientTest {

	@Resource(name = "rs_cxf_client_RestCXFServiceCLient")
	private RestCXFServiceClient restCXFServiceClient;

	@Test
	public void getPersonInstance() {
		Person person = restCXFServiceClient.getPersonInstance();
		assertTrue("Person name should be Ousama Bin Laden",
				"Ousama Bin Laden".equals(person.getName()));
	}

	@Test
	public void getOverloadingPersonInstance() {
		String personName = "Ousama Bin Laden";
		Person person = restCXFServiceClient.getPersonInstance(personName);
		assertTrue("Person name should be " + personName,
				personName.equals(person.getName()));
	}

	@Test
	public void testGetSameUnmodifiedPersonBack() {
		Person person = new Person();
		person.setName("Ousama Bin Laden");
		Person person2 = restCXFServiceClient
				.getSameUnmodifiedPersonBack(person);
		assertTrue("Person name should be equal",
				person.getName().equals(person2.getName()));

	}
}
