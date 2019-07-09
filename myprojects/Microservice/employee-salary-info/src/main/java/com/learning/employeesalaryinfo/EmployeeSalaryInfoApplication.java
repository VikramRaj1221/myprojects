package com.learning.employeesalaryinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeSalaryInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSalaryInfoApplication.class, args);
	}

}
