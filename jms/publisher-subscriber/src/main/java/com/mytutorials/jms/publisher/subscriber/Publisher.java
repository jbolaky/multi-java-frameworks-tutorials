package com.mytutorials.jms.publisher.subscriber;

import java.util.Date;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * This is a simple JMS publisher thread. Once started, it just keeps on sending
 * out periodic messages on ExamplePublishAndSubscribe.TOPIC1.
 */
public class Publisher extends Thread {
    private static Logger jdkLogger = Logger
            .getLogger("com.zcage.jms.Publisher");

    private static final int SEND_INTERVAL_MSEC = 1000;

    private boolean bRunning = false;

    private boolean bStopped = false;

    public int msgCount = 0;

    public Publisher() {
        super("Publisher");
    }

    /**
     * Stop the publishing loop and exit this thread.
     */
    public synchronized void stopPublishing() {
        jdkLogger.info("Publisher.stopPublishing()");
        bRunning = false;
        while (!bStopped) {
            jdkLogger
                    .info("Publisher.stopPublishing(). Waiting for connections to close...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        Connection connection = null;
        bRunning = true;
        while (bRunning) {
            try {
                ++msgCount;
                jdkLogger.info("Publish.run() - message: " + msgCount);
    
                ConnectionFactory factory = ExamplePublishAndSubscribe
                        .getJmsConnectionFactory();
                connection = factory.createConnection();
                connection.start();
    
                Session session = connection.createSession(false,
                        Session.AUTO_ACKNOWLEDGE); // false=NotTransacted
                Topic topic = session
                        .createTopic(ExamplePublishAndSubscribe.TOPIC1);
    
                MessageProducer producer = session.createProducer(topic);
                producer.setTimeToLive(10000);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    
                TextMessage message = session
                        .createTextMessage("Here is a message [" + msgCount
                                + "] at: " + new Date());
                producer.send(message);
    
                connection.close(); // In a real world application, you may want
                                    // to keep
                connection = null; // the connection open for performance.
    
                if (bRunning) {
                    Thread
                            .sleep((int) (Math.random() * 2 * SEND_INTERVAL_MSEC));
                }
            }
    
            catch (Exception e) {
                jdkLogger.info("Exception occurred: " + e.toString());
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                    }
                }
            }
        } // End while
    
        bStopped = true;
        synchronized (this) {
            notifyAll();
        }
        jdkLogger.info("Publisher.run(). Stopped.");
        }
    }
