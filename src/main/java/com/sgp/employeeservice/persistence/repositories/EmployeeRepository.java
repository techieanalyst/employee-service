package com.sgp.employeeservice.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sgp.employeeservice.persistence.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

	List<Employee> findByBasicInformationAgeLessThan(Integer age);
}
