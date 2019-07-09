package com.learning.employeeinfo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Employee {

	@Id
	private Long empId;
	
	private String empName;
	private String designation;
	private String business;
	private BigDecimal salary;
	
	@Transient
	private int port;
	
	public Employee() {
		
	}

	public Employee(Long empId, String empName,
			String designation, String business,
			int port) {
		this.empId=empId;
		this.empName=empName;
		this.designation=designation;
		this.business=business;
		this.port=port;
	}
	
	
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
