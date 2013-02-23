package com.mytutorials.spring.jms.pointtopoint.producer.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mytutorials.spring.jms.pointtopoint.messageconverter.CustomerMessageConverter;
import com.mytutorials.spring.jms.pointtopoint.model.Customer;
import com.mytutorials.spring.jms.pointtopoint.producer.api.Producer;

@Component
public class DefaultProducerImpl implements Producer {

	@Value("${jms.customer.destination}")
	private String customerDestination;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private CustomerMessageConverter customerMessageConverter;

	private Log log = LogFactory.getLog(getClass());

	@Transactional
	public void sendCustomerUpdate(final Customer customer) throws Exception {
		this.jmsTemplate.send(this.customerDestination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				log.info("Sending customer data "
						+ ToStringBuilder.reflectionToString(customer));
				return customerMessageConverter.fromCustomer(session, customer);
			}
		});
	}

	public void sendCustomer(Customer customer) throws Exception {

		this.sendCustomerUpdate(customer);
	}
}
