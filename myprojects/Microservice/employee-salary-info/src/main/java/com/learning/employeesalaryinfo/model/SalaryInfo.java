package com.learning.employeesalaryinfo.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class SalaryInfo {

	public SalaryInfo() {}
	
	public SalaryInfo(Long empId, BigDecimal salary, int port) {
		this.empId=empId;
		this.salary=salary;
		this.port=port;
	}
	
	@Id
	private Long empId;
	private BigDecimal salary;
	
	@Transient
	private int port;
	
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
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
