package com.mytutorials.spring.ws.cxf.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import net.sf.oval.constraint.NotNull;

import com.mytutorials.spring.ws.cxf.common.entity.Message;
import com.mytutorials.spring.ws.cxf.common.service.api.ContactUsService;

@WebService(endpointInterface = "com.mytutorials.spring.ws.cxf.common.service.api.ContactUsService")
public class DefaultContactUsService implements ContactUsService {

	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		messages.add(new Message("Willie", "Wheeler", "willie.wheeler@xyz.com",
				"Great job"));
		messages.add(new Message("Dardy", "Chen", "dardy.chen@xyz.com",
				"I want my money back"));
		return messages;
	}

	public String getMessagesAsString() {
		StringBuilder messagesString = new StringBuilder();
		List<Message> messages = getMessages();
		for (Message message : messages) {
			messagesString.append(message.getLastNameFirstName() + ": "
					+ message.getText() + "\n");
		}
		return messagesString.toString();
	}

	public void postMessage(@NotNull Message message) {
		System.out.println("Hello " + message);
	}

}