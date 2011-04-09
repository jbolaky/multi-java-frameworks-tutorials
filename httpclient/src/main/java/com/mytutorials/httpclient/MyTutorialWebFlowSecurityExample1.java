package com.mytutorials.httpclient;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class MyTutorialWebFlowSecurityExample1 {

	private static final String LOGON_SITE = "localhost";
	private static final int LOGON_PORT = 8080;
	private static final String DOMAIN_URL = "/webflow/loginProcess";

	public static void main(String[] args) {
		try {
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT, "http");
			PostMethod method = new PostMethod(DOMAIN_URL);

			// Prepare login parameters
			NameValuePair userid = new NameValuePair("j_username", "user1");
			NameValuePair password = new NameValuePair("j_password", "1111");
			method.setRequestBody(new NameValuePair[] { userid, password });

			// Configure the form parameters
			// method.addParameter("j_username", "user1");
			// method.addParameter("j_password", "1111");

			// Execute the POST method
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			if (statusCode != -1) {

				System.out.println("Login form post: " + method.getStatusLine().toString());
				String contents = method.getResponseBodyAsString();

				CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
				// See if we got any cookies
				// The only way of telling whether logon succeeded is
				// by finding a session cookie
				Cookie[] logoncookies = cookiespec.match(LOGON_SITE, LOGON_PORT, DOMAIN_URL, false, client.getState()
						.getCookies());
				System.out.println("Logon cookies:");
				if (logoncookies.length == 0) {
					System.out.println("None");
				} else {
					for (int i = 0; i < logoncookies.length; i++) {
						System.out.println("- " + logoncookies[i].toString());
					}
				}
				// Usually a successful form-based login results in a redicrect
				// to
				// another url
				int statuscode = method.getStatusCode();
				if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
						|| (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
					Header header = method.getResponseHeader("location");
					if (header != null) {
						String newuri = header.getValue();
						if ((newuri == null) || (newuri.equals(""))) {
							newuri = "/";
						}
						System.out.println("Redirect target: " + newuri);
						GetMethod redirect = new GetMethod(newuri);

						client.executeMethod(redirect);
						System.out.println("Redirect: " + redirect.getStatusLine().toString());
						System.out.println("Redirect: " + redirect.getResponseBodyAsString());
						// release any connection resources used by the method
						redirect.releaseConnection();
					} else {
						System.out.println("Invalid redirect");
						System.exit(1);
					}
				}

				System.out.println(contents);
				method.releaseConnection();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}