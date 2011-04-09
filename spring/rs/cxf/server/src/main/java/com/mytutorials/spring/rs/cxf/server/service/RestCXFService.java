package com.mytutorials.spring.rs.cxf.server.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.mytutorials.spring.rs.cxf.entity.Person;

@Path("/RestCXFService/")
public class RestCXFService {

	private static final Logger LOGGER = Logger.getLogger(RestCXFService.class);

	@GET
	@Path("/getPersonInstance")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPersonInstance() {
		LOGGER.debug("In method Person getPersonInstance()");
		Person person = new Person();
		person.setName("Ousama Bin Laden");
		return person;
	}

	@GET
	@Path("/getPersonInstance/{personName}")
	@Produces(MediaType.APPLICATION_XML)
	public Person getPersonInstance(
			@PathParam(value = "personName") String personName) {
		LOGGER.debug("In method Person getPersonInstance(String personName) with personName "
				+ personName);
		Person person = new Person();
		person.setName(personName);
		return person;
	}

	@POST
	@Path("/getSameUnmodifiedPersonBack")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Person getSameUnmodifiedPersonBack(Person person) {
		LOGGER.debug("In method Person getSameUnmodifiedPersonBack(Person person) with person = "
				+ person.toString());
		return person;
	}

}
