package com.sgp.employeeservice.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sgp.employeeservice.api.EmployeeRequest;
import com.sgp.employeeservice.persistence.model.BasicInformation;
import com.sgp.employeeservice.persistence.model.Employee;

@Component
public class EmployeeRequestToEmployeeConverter implements Converter<EmployeeRequest, Employee>{

	@Override
	public Employee convert(EmployeeRequest source) {
		Employee employee = new Employee();
		BasicInformation information = new BasicInformation(source.getName(), source.getAge());
		employee.setEmail(source.getEmail());
		employee.setPhone(source.getPhone());
		employee.setBasicInformation(information);
		employee.setCompany(source.getCompany());
		employee.setJoinDate(source.getJoinDate());
		employee.setSalary(source.getSalary());
		if (source.getAddress() != null) {
			employee.setCity(source.getAddress().getCity());
			employee.setStreet(source.getAddress().getStreet());
			employee.setZipcode(source.getAddress().getZipcode());
		}
		return employee;
	}

}
