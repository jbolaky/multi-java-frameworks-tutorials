package com.mytutorials.jms.publisher.subscriber;

import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * A simple JMS MessageListener who listens on
 * ExamplePublishAndSubscribe.TOPIC1.
 */
public class Subscriber implements MessageListener {
    private static Logger jdkLogger = Logger
            .getLogger("com.zcage.jms.Publisher");

    private Connection connection;

    public void startListening() {
        jdkLogger.info("Subscriber.startListening()");

        try {
            ConnectionFactory factory = ExamplePublishAndSubscribe
                    .getJmsConnectionFactory();
            connection = factory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Topic topic = session
                    .createTopic(ExamplePublishAndSubscribe.TOPIC1);
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(this);

            connection.start();
        } catch (JMSException e) {
            jdkLogger.info("Exception occurred: " + e.toString());
        }
    }

    /**
     * Just log a note when a message is received.
     */
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) message;
            try {
                jdkLogger.info("Subscriber.onMessage(): " + txtMsg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stop and close the JMS connection which terminates the listener
     */
    public void stopListening() {
        jdkLogger.info("Subscriber.stopListening()");
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
