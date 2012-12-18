package com.mytutorials.jms.pointtopoint;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.broker.BrokerService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import com.mytutorials.jms.pointtopoint.consumer.api.Consumer;
import com.mytutorials.jms.pointtopoint.consumer.impl.DefaultConsumerImpl;
import com.mytutorials.jms.pointtopoint.producer.api.Producer;
import com.mytutorials.jms.pointtopoint.producer.impl.DefaultProducerImpl;

public class ProducerConsumerImplIntegrationTest {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	// Name of the queue we will be sending messages to
	private String SUBJECT = "TESTQUEUE";

	private Producer producer = null;

	private Consumer consumer = null;

	@Before
	public void setUp() throws Exception{
		
		BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616");
        broker.start();
		
		producer = new DefaultProducerImpl(URL, SUBJECT);
		
		consumer = new DefaultConsumerImpl(URL, SUBJECT);
		
	}
	
	@Test
	public void testProducerConsumer() throws JMSException{
		
		String message = "Hello Word";
		
		producer.sendMessage(message);
		
		assertEquals(message, consumer.consumeMessage());
	}
}
