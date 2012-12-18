package com.mytutorials.jms.publisher.subscriber;

import java.util.Date;

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

/**
 * Running as a separate thread the Receiver waits for messages to arrive.
 * When a message arrives, it is processed with the reply sent back to the
 * sender.
 */
public class Receiver extends Thread {

	private boolean bRunning = false;
	private boolean bStopped = false;
	
	public void run() {
        System.out.println("Receiver() starting");
        bRunning = true;
        
        Connection connection = null;
        try
        {
            ConnectionFactory factory = ExampleSendAndReceive.getJmsConnectionFactory();
            connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
            Queue queue = session.createQueue(ExampleSendAndReceive.QUEUE1);

            MessageConsumer consumer = session.createConsumer(queue);

            while (bRunning)
            {
                Message message = consumer.receive(1000);
                if (message instanceof TextMessage)
                {
                    String msg = ((TextMessage) message).getText();
                    System.out.println("[Receiver] received message: " + msg);
                    // Send a response message back
                    if (message.getJMSReplyTo() != null)
                    {
                        MessageProducer replyProducer = session.createProducer(message.getJMSReplyTo());
                        replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                        replyProducer.send(session.createTextMessage("Got your message [" + msg + "] - "
                                + new Date()));
                    }
                }
            }
        }
        catch (JMSException e)
        {
            System.out.println("Exception occurred: " + e.toString());
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (Exception e)
            {
            }
            System.out.println("Receiver ended");
            bStopped = true;
            synchronized (this) {notifyAll();}
        }
	}

	synchronized public void stopListening() {
		System.out.println("Receiver.stopListening()");
		bRunning = false;
		while (!bStopped) 
		{
			System.out.println("Receiver.stopListening().Waiting for connections to close...");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
