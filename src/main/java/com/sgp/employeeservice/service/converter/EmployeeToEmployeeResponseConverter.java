package com.sgp.employeeservice.service.converter;

import org.h2.util.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sgp.employeeservice.api.AddressResponse;
import com.sgp.employeeservice.api.EmployeeResponse;
import com.sgp.employeeservice.persistence.model.BasicInformation;
import com.sgp.employeeservice.persistence.model.Employee;

@Component
public class EmployeeToEmployeeResponseConverter implements Converter<Employee, EmployeeResponse> {

	@Override
	public EmployeeResponse convert(Employee source) {
		EmployeeResponse employee = new EmployeeResponse();
		BasicInformation information = source.getBasicInformation();
		employee.setAge(information.getAge());
		employee.setCompany(source.getCompany());
		employee.setEmail(source.getEmail());
		employee.setJoinDate(source.getJoinDate());
		employee.setName(information.getName());
		employee.setPhone(source.getPhone());
		employee.setSalary(source.getSalary());
		if (!StringUtils.isNullOrEmpty(source.getCity()) || !StringUtils.isNullOrEmpty(source.getStreet())
				|| !StringUtils.isNullOrEmpty(source.getZipcode())) {
			AddressResponse address = new AddressResponse(source.getStreet(), source.getCity(), source.getZipcode());
			employee.setAddress(address);
		}
		return employee;
	}

}
