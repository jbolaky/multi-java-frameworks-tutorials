package com.mytutorials.jms.pointtopoint.producer.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.mytutorials.jms.pointtopoint.producer.api.Producer;

public class DefaultProducerImpl implements Producer {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	// Name of the queue we will be sending messages to
	private String SUBJECT = "TESTQUEUE";

	public DefaultProducerImpl(String uRL, String sUBJECT) {
		super();
		URL = uRL;
		SUBJECT = sUBJECT;
	}

	public void sendMessage(String message) throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				this.URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// JMS messages are sent and received using a Session. We will
		// create here a non-transactional session object. If you want
		// to use transactions you should set the first parameter to 'true'
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Destination represents here our queue 'TESTQUEUE' on the
		// JMS server. You don't have to do anything special on the
		// server to create it, it will be created automatically.
		Destination destination = session.createQueue(this.SUBJECT);

		// MessageProducer is used for sending messages (as opposed
		// to MessageConsumer which is used for receiving them)
		MessageProducer producer = session.createProducer(destination);

		// We will send a small text message saying 'Hello' in Japanese
		TextMessage messTextMessage = session.createTextMessage(message);

		// Here we are sending the message!
		producer.send(messTextMessage);
		System.out.println("Message successfully sent " + messTextMessage.getText() + "'");

		connection.close();
	}

}
