package com.mytutorials.spring.jms.pointtopoint.consumer.impl;

import org.apache.activemq.broker.BrokerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorials.spring.jms.pointtopoint.model.Customer;
import com.mytutorials.spring.jms.pointtopoint.producer.api.Producer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/jms-jmstemplate-consumer.xml" })
public class RawJmsTemplatePollingMessageConsumerTest {

	@Autowired
	private Producer producer;

	private BrokerService broker;
	
	@Autowired
	private RawJmsTemplatePollingMessageConsumer rawJmsTemplatePollingMessageConsumer;

	@Before
	public void setUp() throws Exception {

		broker = new BrokerService();
		broker.setUseJmx(true);
		broker.addConnector("tcp://localhost:61616");
		broker.start();

	}

	@Test
	public void testReceiveAndProcessCustomerUpdates() throws Exception {

		producer.sendCustomer(new Customer(1, "John", "Doe", "jd@email.com"));

		rawJmsTemplatePollingMessageConsumer.receiveAndProcessCustomerUpdates();
		
		broker.stop();
	}
	
}
