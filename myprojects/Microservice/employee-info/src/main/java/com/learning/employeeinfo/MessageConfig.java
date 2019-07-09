package com.learning.employeeinfo;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("employeeinfo")
public class MessageConfig {

	private String business;

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
}
