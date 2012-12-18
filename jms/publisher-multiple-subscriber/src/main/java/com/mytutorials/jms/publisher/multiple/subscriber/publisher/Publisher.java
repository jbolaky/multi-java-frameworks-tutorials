package com.mytutorials.jms.publisher.multiple.subscriber.publisher;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Publisher extends Thread {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Publisher.class);

	private Connection connection = null;

	private String message = null;

	private String topicName = null;

	public Publisher(Connection connection, String message, String topicName) {

		super("Publisher");
		this.connection = connection;
		this.message = message;
		this.topicName = topicName;
	}

	public synchronized void stopPublishing() {
		LOGGER.info("Publisher.stopPublishing()");
		LOGGER.info("Publisher.stopPublishing(). Waiting for connections to close...");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicName);

			MessageProducer producer = session.createProducer(topic);
			producer.setTimeToLive(10000);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(this.message);
			producer.send(message);
			
			connection.close();

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
