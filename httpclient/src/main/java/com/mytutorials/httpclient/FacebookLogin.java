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

public class FacebookLogin {

	private static final String LOGON_SITE = "https://login-13-04-snc4.facebook.com";
	private static final String DOMAIN_URL = "/login.php?login_attempt=1";

	public static void main(String[] args) {
		try {
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod("https://login.facebook.com/login.php?login_attempt=1");

			method.addParameter("charset_test", "%E2%82%AC%2C%C2%B4%2C%E2%82%AC%2C%C2%B4%2C%E6%B0%B4%2C%D0%94%2C%D0%84");
			method.addParameter("lsd", "Ee-V4");
			method.addParameter("locale", "en_GB");
			method.addParameter("email", "crazy.bond07%40gmail.com");
			method.addParameter("pass", "70dnob.yzarc");
			method.addParameter("persistent", "1");
			method.addParameter("default_persistent", "1");
			method.addParameter("charset_test", "%E2%82%AC%2C%C2%B4%2C%E2%82%AC%2C%C2%B4%2C%E6%B0%B4%2C%D0%94%2C%D0%84");
			method.addParameter("lsd", "Ee-V4");
			

			// Execute the POST method
			int statusCode = client.executeMethod(method);
			System.out.println(statusCode);
			if (statusCode != -1) {

				System.out.println("Login form post: " + method.getStatusLine().toString());
				String contents = method.getResponseBodyAsString();

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