package com.mytutorials.jms.publisher.multiple.subscriber.subscriber;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subscriber implements MessageListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Subscriber.class);

	private Connection connection = null;

	private String topicName = null;

	public Subscriber(Connection connection, String topicName) {
		
		super();
		this.connection = connection;
		this.topicName = topicName;
	}

	public void startListening() {

		LOGGER.info("Subscriber.startListening()");

		try {

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicName);
			MessageConsumer consumer = session.createConsumer(topic);
			consumer.setMessageListener(this);

			connection.start();
		} catch (JMSException e) {

			LOGGER.info("Exception occurred: " + e.toString());
		}
	}

	/**
	 * Just log a note when a message is received.
	 */
	public void onMessage(Message message) {

		if (message instanceof TextMessage) {

			TextMessage txtMsg = (TextMessage) message;
			try {

				LOGGER.info("Subscriber.onMessage(): " + txtMsg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Stop and close the JMS connection which terminates the listener
	 */
	public void stopListening() {

		LOGGER.info("Subscriber.stopListening()");

		if (connection != null) {
			try {
				connection.stop();
				connection.close();
			} catch (JMSException e) {
			} finally {
				connection = null;
			}
		}
	}
}
