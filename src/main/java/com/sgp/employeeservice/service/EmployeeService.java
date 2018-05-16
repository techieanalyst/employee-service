package com.sgp.employeeservice.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import com.sgp.employeeservice.api.EmployeeRequest;
import com.sgp.employeeservice.api.EmployeeResponse;
import com.sgp.employeeservice.exception.EmployeeQueryException;
import com.sgp.employeeservice.persistence.model.BasicInformation;
import com.sgp.employeeservice.persistence.model.Employee;
import com.sgp.employeeservice.persistence.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	@Qualifier("mvcConversionService")
	private ConversionService conversionService;

	@Transactional
	public List<EmployeeResponse> persistEmployeeRequestEntries(List<EmployeeRequest> requests) {
		List<Employee> employees = employeeRepository.saveAll(requests.stream()
				.map(employee -> conversionService.convert(employee, Employee.class)).collect(Collectors.toList()));
		return employees.stream().map(employee -> conversionService.convert(employee, EmployeeResponse.class))
				.collect(Collectors.toList());
	}

	public List<EmployeeResponse> getAllEmployeesYoungerThanAge(Integer age) throws EmployeeQueryException {
		if (Math.signum(age) <= 0)
			throw new EmployeeQueryException("invalid input parameter");
		List<Employee> employees = employeeRepository.findByBasicInformationAgeLessThan(age);
		return employees.stream().map(employee -> conversionService.convert(employee, EmployeeResponse.class))
				.collect(Collectors.toList());
	}

	public List<EmployeeResponse> getAllEmployeesSortedByAttribute(String attribute) throws EmployeeQueryException {
		StringBuilder transformedAttribute = new StringBuilder();
		for (Field f : BasicInformation.class.getDeclaredFields()) {
			if (f.getName().equalsIgnoreCase(attribute)) {
				transformedAttribute.append("basicInformation.");
				break;
			}
		}
		transformedAttribute.append(attribute);
		try {
			List<Employee> employees = employeeRepository
					.findAll(Sort.by(Direction.ASC, transformedAttribute.toString()));
			return employees.stream().map(employee -> conversionService.convert(employee, EmployeeResponse.class))
					.collect(Collectors.toList());
		} catch (PropertyReferenceException e) {
			throw new EmployeeQueryException("invalid parameter for sorting");
		}
	}
}
