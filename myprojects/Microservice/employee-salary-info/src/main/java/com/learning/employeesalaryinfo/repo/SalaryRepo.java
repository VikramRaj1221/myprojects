package com.learning.employeesalaryinfo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.learning.employeesalaryinfo.model.SalaryInfo;

@Component
public interface SalaryRepo extends JpaRepository<SalaryInfo,Long>{

}
