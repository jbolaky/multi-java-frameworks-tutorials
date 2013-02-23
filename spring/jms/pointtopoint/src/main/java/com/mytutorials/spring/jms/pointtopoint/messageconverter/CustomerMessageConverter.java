package com.mytutorials.spring.jms.pointtopoint.messageconverter;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.stereotype.Component;

import com.mytutorials.spring.jms.pointtopoint.model.Customer;

@Component
public class CustomerMessageConverter {

	private static String WELL_KNOWN_HEADER_ID = "id";
	private static String WELL_KNOWN_HEADER_FIRST_NAME = "firstName";
	private static String WELL_KNOWN_HEADER_EMAIL = "email";
	private static String WELL_KNOWN_HEADER_LAST_NAME = "lastName";

	public Message fromCustomer(Session session, Customer customer)
			throws JMSException {

		MapMessage mapMessage = session.createMapMessage();
		mapMessage.setLong(WELL_KNOWN_HEADER_ID, customer.getId());
		mapMessage.setString(WELL_KNOWN_HEADER_FIRST_NAME,
				customer.getFirstName());
		mapMessage.setString(WELL_KNOWN_HEADER_LAST_NAME,
				customer.getLastName());
		mapMessage.setString(WELL_KNOWN_HEADER_EMAIL, customer.getEmail());

		return mapMessage;
	}

	public Customer toCustomer(MapMessage message) throws JMSException {

		String firstName = message.getString(WELL_KNOWN_HEADER_FIRST_NAME);
		String lastName = message.getString(WELL_KNOWN_HEADER_LAST_NAME);
		String email = message.getString(WELL_KNOWN_HEADER_EMAIL);
		Long id = message.getLong(WELL_KNOWN_HEADER_ID);
		return new Customer(id, firstName, lastName, email);
	}
}
