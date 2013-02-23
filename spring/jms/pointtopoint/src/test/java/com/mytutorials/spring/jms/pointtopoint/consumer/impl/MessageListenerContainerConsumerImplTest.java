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
@ContextConfiguration(locations = { "classpath:/spring-jms-point-to-point-message-listener-container-consumer.xml" })
public class MessageListenerContainerConsumerImplTest {

	@Autowired
	private Producer producer;

	private BrokerService broker;
	
	@Before
	public void setUp() throws Exception {

		broker = new BrokerService();
		broker.setUseJmx(true);
		broker.addConnector("tcp://localhost:61616");
		broker.start();

	}

	@Test
	public void testProducerAndMessageListener() throws Exception {

		producer.sendCustomer(new Customer(1, "John", "Doe", "jd@email.com"));
		
		broker.stop();
	}

}
