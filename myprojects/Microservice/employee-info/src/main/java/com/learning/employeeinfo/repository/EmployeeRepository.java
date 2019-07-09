package com.learning.employeeinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.learning.employeeinfo.model.Employee;

@Component
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
