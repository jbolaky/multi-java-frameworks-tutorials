package com.mytutorials.spring.ws.cxf.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mytutorials.spring.ws.cxf.common.entity.Message;
import com.mytutorials.spring.ws.cxf.common.service.api.ContactUsService;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:default-cxf-client-context.xml" })
public class WebServiceCXFClientTest {

	@Resource(name = "webServiceCXFClient")
	private WebServiceCXFClient webServiceCXFClient;

	@Test
	public void testGetMessageAsString() throws Exception {
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(ContactUsService.class);
		factory.setAddress("http://localhost:8080/spring.ws.cxf/webservices/contactus/");
		ContactUsService client = (ContactUsService) factory.create();

		String response = client.getMessagesAsString();
		System.out.println("Received response from webservice: " + response);

		assertTrue(response.contains("Wheeler"));
	}

	@Test
	public void testGetMessage() {
		List<Message> messages = webServiceCXFClient.getMessages();
		assertEquals(2, messages.size());
		assertEquals("Willie", messages.get(0).getFirstName());

	}

	@Test
	public void testPostMessage() {
		webServiceCXFClient.postMessage(new Message("Javaid", "Bolaky", null,
				null));
	}

	@Test(expected = SOAPFaultException.class)
	public void testPostMessageNull() {
		try {
			webServiceCXFClient.postMessage(null);
		} catch (WebServiceException e) {
			assertEquals(
					"com.mytutorials.spring.ws.cxf.server.service.impl.DefaultContactUsService.postMessage(class com.mytutorials.spring.ws.cxf.common.entity.Message) parameter 0 (arg0) cannot be null",
					e.getMessage());
			throw e;
		}

	}
}
