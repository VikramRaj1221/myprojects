package com.learning.employeeinfo.repository;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.employeeinfo.model.SalaryInfo;

@RibbonClient(name="employee-salary-info")
@FeignClient(name="employee-salary-info")
public interface EmployeeFeignService {

	@GetMapping("/salary/{empId}")
	public SalaryInfo getSalaryInfo(@PathVariable("empId") int empId);
	
}
