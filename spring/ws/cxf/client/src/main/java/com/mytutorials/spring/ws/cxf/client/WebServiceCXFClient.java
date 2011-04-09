package com.mytutorials.spring.ws.cxf.client;

import java.util.List;

import javax.annotation.Resource;

import com.mytutorials.spring.ws.cxf.common.entity.Message;
import com.mytutorials.spring.ws.cxf.common.service.api.ContactUsService;

public class WebServiceCXFClient {

	@Resource(name="contactUsServiceClient")
	private ContactUsService contactUsService;

	public List<Message> getMessages() {
		return contactUsService.getMessages();
	}

	public String getMessagesAsString() {
		return contactUsService.getMessagesAsString();
	}

	public void postMessage(Message message) {
		contactUsService.postMessage(message);
	}
}
