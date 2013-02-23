package com.mytutorials.spring.jms.pointtopoint.producer.api;

import com.mytutorials.spring.jms.pointtopoint.model.Customer;

public interface Producer {

	void sendCustomer(Customer customer) throws Exception;
}
