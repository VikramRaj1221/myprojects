package com.learning.jwtsecuritytest.conroller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
public class Token {

	
	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// This specifies that we are using a JWT assertion
		// to authenticate
		params.add(new BasicNameValuePair(
		  "grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer"));
		// Our JWT assertion
		params.add(new BasicNameValuePair(
		  "assertion", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJpc3MiOiJsb3ZyNmxna28wNDAwa2dub2M2YTFsdW93cW04bTMxdyIsInN1YiI6IjE4NjYwMyIsImJveF9zdWJfdHlwZSI6ImVudGVycHJpc2UiLCJhdWQiOiJodHRwczpcL1wvYXBpLmJveC5jb21cL29hdXRoMlwvdG9rZW4iLCJqdGkiOiJ3RVR3dzdISW4wNCttdWpMR1NOYVRGZUxoOTk2RFcrUEg5Z1JOZVIyU1duK3BsaWpCSGVEek9CVHF1dFRVZjJSbEhLeFwvYktKK010RHRzY2NDSUgwM3c9PSIsImV4cCI6MTU2MTcxMzkzMCwia2lkIjoiemNkYjZlOWwifQ.0knflHNpu5NOmi_AfzUXiPsaP8kT3tY13Kry6p-tZ4goLegcfISRzlj4p3taCDaEjto9OMxSLn5UBFR7cXR8LaClubJqWfLqcymIIYQl_KSDNhIOZ2GMF4Q73yo0ow3iLlRIpNne1bgi7MEHPSIfNi10pC2iIhcOQqdjSFEk0fjT6E7XRSGs40Z7tdFPs_s-gK6_dum6O4W5pp4v0XTjHpy9s1jImvtYAJXl-qXc4oocZij8wZNCLsHXVGQ4_lZHtJDeRmJvp632CM82xV7dumWtMjPO3mv1aHXM8G3O-x7o-ECKUHC4EmTFnD6R9uZDwWa5OUm_Vfp3Ua32bNnMEA"));
		// The OAuth 2 client ID and secret
		params.add(new BasicNameValuePair(
		  "client_id", "lovr6lgko0400kgnoc6a1luowqm8m31w"));
		params.add(new BasicNameValuePair(
		  "client_secret", "b7QcJDptDWcTnVvvRnGboyPsX6UuGsIC"));

		// Make the POST call to the authentication endpoint
		 
		try {
			CloseableHttpClient httpClient =
					  HttpClientBuilder.create().disableCookieManagement().build();
					HttpPost request = new HttpPost("https://api.box.com/oauth2/token");
			request.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpResponse httpResponse = httpClient.execute(request);
			HttpEntity entity = httpResponse.getEntity();
			String response = EntityUtils.toString(entity);
			System.out.println("Before response ");
			System.out.println("response "+response);
			httpClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
