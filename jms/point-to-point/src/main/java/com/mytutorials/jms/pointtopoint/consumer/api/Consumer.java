package com.mytutorials.jms.pointtopoint.consumer.api;

import javax.jms.JMSException;

public interface Consumer {

	String consumeMessage() throws JMSException;
}
