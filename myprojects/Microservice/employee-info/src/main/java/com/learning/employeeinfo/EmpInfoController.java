package com.learning.employeeinfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.learning.employeeinfo.model.Employee;
import com.learning.employeeinfo.model.SalaryInfo;
import com.learning.employeeinfo.repository.EmployeeFeignService;
import com.learning.employeeinfo.repository.EmployeeRepository;

@RequestMapping("/employeeInfo")
@RestController
public class EmpInfoController {

	@Autowired
	private MessageConfig message;
	
	@Autowired
	private EmployeeRepository emprepo;
	
	@Autowired
	private EmployeeFeignService service;
	
	@GetMapping("/getData/{empId}")
	public Employee getEmpInfo(@PathVariable("empId") int empId ) {
		
		System.out.println("message" +message.getBusiness());
		
		Map<String,Integer> resMap=new HashMap<String,Integer>();
		resMap.put("empId", empId);
		
		SalaryInfo salary=service.getSalaryInfo(empId);
		
		/*String url= "http://localhost:8085/salary/{empId}";
		
		RestTemplate templete=new RestTemplate();
		SalaryInfo salary=templete.getForEntity(url, SalaryInfo.class,resMap).getBody();*/
		
		Employee empOpt=emprepo.findById(new Long(empId)).get();
		empOpt.setSalary(salary.getSalary());
		empOpt.setPort(salary.getPort());
		return empOpt;
		
	}
	
}
