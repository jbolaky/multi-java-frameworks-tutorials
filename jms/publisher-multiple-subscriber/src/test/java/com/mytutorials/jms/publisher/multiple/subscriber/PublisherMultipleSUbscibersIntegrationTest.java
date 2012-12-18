package com.mytutorials.jms.publisher.multiple.subscriber;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.Before;
import org.junit.Test;

import com.mytutorials.jms.publisher.multiple.subscriber.publisher.Publisher;
import com.mytutorials.jms.publisher.multiple.subscriber.subscriber.Subscriber;

public class PublisherMultipleSUbscibersIntegrationTest {

	public static final String TOPIC = "Simple.Test.Topic1";
	
	private Publisher publisher = null;

	private Subscriber subscriber1 = null;

	private Subscriber subscriber2 = null;

	@Before
	public void setUp() throws Exception {

		BrokerService broker = new BrokerService();
		broker.setUseJmx(true);
		broker.addConnector("tcp://localhost:61616");
		broker.start();
		
		publisher = new Publisher(getJmsConnection(), "Hello World", PublisherMultipleSUbscibersIntegrationTest.TOPIC);
		
		subscriber1 = new Subscriber(getJmsConnection(), TOPIC);
		
		subscriber2 = new Subscriber(getJmsConnection(), TOPIC);
	}
	
	@Test
	public void testPublisherWithMultipleSUbscibers() throws InterruptedException{
		
		publisher.start();
		
		subscriber1.startListening();
		subscriber2.startListening();
		
		Thread.sleep(10000);
		subscriber1.stopListening();
		subscriber2.stopListening();
	}

	public Connection getJmsConnection() throws JMSException {
		String user = ActiveMQConnection.DEFAULT_USER;
		String password = ActiveMQConnection.DEFAULT_PASSWORD;
		String url = ActiveMQConnection.DEFAULT_BROKER_URL;

		return new ActiveMQConnectionFactory(user, password, url)
				.createConnection();
	}

}
