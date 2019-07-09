package com.learning.employeesalaryinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.employeesalaryinfo.model.SalaryInfo;
import com.learning.employeesalaryinfo.repo.SalaryRepo;

@RestController
public class EmployeeSalaryController {

	@Autowired
	private SalaryRepo salaryrepo;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/salary/{empId}")
	public SalaryInfo getSalaryInfo(@PathVariable("empId") int empId) {
		int port= Integer.parseInt(environment.getProperty("local.server.port"));
		System.out.println("port "+port);
		SalaryInfo salary=salaryrepo.findById(new Long(empId)).get();
		salary.setPort(port);
		return salary;
	}
	
}
