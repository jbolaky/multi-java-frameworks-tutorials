package com.mytutorials.jms.pointtopoint.consumer.impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.mytutorials.jms.pointtopoint.consumer.api.Consumer;

public class DefaultConsumerImpl implements Consumer {

	// URL of the JMS server. DEFAULT_BROKER_URL will just mean
	// that JMS server is on localhost
	private String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	// Name of the queue we will be sending messages to
	private String SUBJECT = "TESTQUEUE";

	public DefaultConsumerImpl(String uRL, String sUBJECT) {
		super();
		URL = uRL;
		SUBJECT = sUBJECT;
	}

	public String consumeMessage() throws JMSException {

		String message = null;

		// Getting JMS connection from the server
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		// Creating session for seding messages
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// Getting the queue 'TESTQUEUE'
		Destination destination = session.createQueue(SUBJECT);

		// MessageConsumer is used for receiving (consuming) messages
		MessageConsumer consumer = session.createConsumer(destination);

		// Here we receive the message.
		// By default this call is blocking, which means it will wait
		// for a message to arrive on the queue.
		Message message2 = consumer.receive();

		connection.close();

		// There are many types of Message and TextMessage
		// is just one of them. Producer sent us a TextMessage
		// so we must cast to it to get access to its .getText()
		// method.
		if (message2 instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message2;
			message = textMessage.getText();
			System.out.println("Received message '" + textMessage.getText()
					+ "'");
		}

		return message;
	}

}
