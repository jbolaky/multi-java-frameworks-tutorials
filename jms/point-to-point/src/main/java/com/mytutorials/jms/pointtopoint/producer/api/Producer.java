package com.mytutorials.jms.pointtopoint.producer.api;

import javax.jms.JMSException;

public interface Producer {

	void sendMessage(String message) throws JMSException;
}
