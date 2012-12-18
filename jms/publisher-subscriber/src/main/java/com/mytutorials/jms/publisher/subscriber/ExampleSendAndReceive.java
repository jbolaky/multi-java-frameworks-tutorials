package com.mytutorials.jms.publisher.subscriber;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

/**
 * This is a simple example to show some basics of JMS for a
 * send with reply.
 */
public class ExampleSendAndReceive {
	public static final String QUEUE1 = "Simple.Test.Queue1";
	
	public static void main(String[] args) throws Exception {
		startBroker(); // An embedded JMS Broker

		Receiver receiver = new Receiver();
		receiver.start(); // Runs on a seperate thread
		
		//Send one message manually
		String response = Sender.sendMessage("Hello World");
		System.out.println("Response: " + response);
		Thread.sleep(1000);
		
		receiver.stopListening();
		
		System.exit(0); // Force exit
	}

	/**
	 * Create an embedded JMS Broker for this example. 
	 */
	private static void startBroker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setUseJmx(true);
		broker.addConnector("tcp://localhost:61616");
		broker.start();
	}

	/**
	 * Return a JMS ConnectionFactory with the defaults. 
	 */
	public static ConnectionFactory getJmsConnectionFactory()
			throws JMSException {
		String user = ActiveMQConnection.DEFAULT_USER;
		String password = ActiveMQConnection.DEFAULT_PASSWORD;
		String url = ActiveMQConnection.DEFAULT_BROKER_URL;

		return new ActiveMQConnectionFactory(user, password, url);
	}
}
