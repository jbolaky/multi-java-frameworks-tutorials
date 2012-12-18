package com.mytutorials.jms.publisher.subscriber;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Sender {

	static public String sendMessage(String msgString) {
		ConnectionFactory factory = null;
		Connection connection = null;
		String replyString = null;
		try {
			factory = ExamplePublishAndSubscribe.getJmsConnectionFactory();
			connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
			Queue sendQueue = session.createQueue(ExampleSendAndReceive.QUEUE1);
			MessageProducer producer = session.createProducer(sendQueue);
			
			//Create temp queue for response
			Queue responseQueue = session.createTemporaryQueue();
			MessageConsumer consumer = session.createConsumer(responseQueue);

			producer.setTimeToLive(500); // msecs
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			TextMessage message = session.createTextMessage(msgString);
			message.setJMSReplyTo(responseQueue);
			producer.send(message);
			
			// See if there is a response
			Message replyMessage = consumer.receive(1000);
			if (replyMessage instanceof TextMessage) {
				replyString = ((TextMessage) replyMessage).getText();
			}
			session.close();
			
			return replyString;
		} 
		catch (Exception e) 
		{
			System.out.println("Exception occurred: " + e.toString());
			return null;
			
		} 
		finally 
		{
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
				}
			}
		}
	}
}
