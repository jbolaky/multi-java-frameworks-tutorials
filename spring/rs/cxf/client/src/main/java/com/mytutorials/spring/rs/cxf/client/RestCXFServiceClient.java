package com.mytutorials.spring.rs.cxf.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.util.URIUtil;

import com.mytutorials.spring.rs.cxf.entity.Person;

public class RestCXFServiceClient {

	private URL url;
	private static final HttpClient httpClient = new HttpClient();
	private static final String GET_PERSON_INSTANCE_METHOD_NAME = "getPersonInstance";
	private static final String GET_SAME_MOFIFIED_PERSON_METHOD_NAME = "getSameUnmodifiedPersonBack";

	private static JAXBContext jaxbContext;

	static {

		try {
			jaxbContext = JAXBContext.newInstance(Person.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Person getPersonInstance() {

		InputStream inputStream = getResultForGETMethod(GET_PERSON_INSTANCE_METHOD_NAME);
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Person) unmarshaller.unmarshal(inputStream);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public Person getPersonInstance(String personName) {

		InputStream inputStream = getResultForGETMethod(GET_PERSON_INSTANCE_METHOD_NAME
				+ "/" + personName);
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Person) unmarshaller.unmarshal(inputStream);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public Person getSameUnmodifiedPersonBack(Person person) {

		InputStream inputStream = getResultForPOSTMethod(
				GET_SAME_MOFIFIED_PERSON_METHOD_NAME, person);

		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (Person) unmarshaller.unmarshal(inputStream);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}

	}

	private InputStream getResultForGETMethod(

	String methodNameAndParamaterValues) {
		try {

			GetMethod getMethod = new GetMethod(URIUtil.encodePath(url
					.toString() + methodNameAndParamaterValues));

			httpClient.executeMethod(getMethod);

			return getMethod.getResponseBodyAsStream();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private InputStream getResultForPOSTMethod(String methodName, Object object) {

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		InputStream inputStream;

		Marshaller marshaller;
		try {
			marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(object, byteArrayOutputStream);

			inputStream = new ByteArrayInputStream(
					byteArrayOutputStream.toByteArray());
			RequestEntity entity = new InputStreamRequestEntity(inputStream);

			PostMethod post = new PostMethod(URIUtil.encodePath(url.toString()
					+ methodName));
			post.setRequestEntity(entity);

			httpClient.executeMethod(post);

			return post.getResponseBodyAsStream();

		} catch (JAXBException e) {
			throw new RuntimeException(e);
		} catch (HttpException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
