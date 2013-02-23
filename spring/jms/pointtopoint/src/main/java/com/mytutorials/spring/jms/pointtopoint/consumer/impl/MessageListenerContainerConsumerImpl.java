package com.mytutorials.spring.jms.pointtopoint.consumer.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.mytutorials.spring.jms.pointtopoint.model.Customer;

@Component
public class MessageListenerContainerConsumerImpl {

	private Log log = LogFactory.getLog(getClass());

	public void receiveMessage(Map<String, Object> message) throws Exception {
		String firstName = (String) message.get("firstName");
		String lastName = (String) message.get("lastName");
		String email = (String) message.get("email");
		Long id = (Long) message.get("id");
		Customer customer = new Customer(id, firstName, lastName, email);
		log.info("receiving customer message: " + customer);
	}

}
