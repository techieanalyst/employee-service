package com.sgp.employeeservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgp.employeeservice.api.EmployeeListResponse;
import com.sgp.employeeservice.api.EmployeeRequest;
import com.sgp.employeeservice.api.EmployeeResponse;
import com.sgp.employeeservice.api.ValidList;
import com.sgp.employeeservice.api.WebServiceResponse;
import com.sgp.employeeservice.exception.EmployeeQueryException;
import com.sgp.employeeservice.exception.EmployeeValidationException;
import com.sgp.employeeservice.service.EmployeeService;

@RestController
@RequestMapping(path = "v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

	private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Validator validator;

	@PostMapping(path = "/employees/bulkupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<? extends WebServiceResponse> saveEmployeesByBulk(@RequestParam("file") MultipartFile file)
			throws IOException, EmployeeValidationException {
		logger.info("saving data");
		InputStream inputStream = file.getInputStream();
		if (!file.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, EmployeeRequest.class);
			ArrayList<EmployeeRequest> requests = mapper.readValue(inputStream, type);
			ValidList<EmployeeRequest> validList = new ValidList<EmployeeRequest>(requests);
			Set<ConstraintViolation<ValidList<EmployeeRequest>>> constraintValidationResults = validator
					.validate(validList);
			if (!constraintValidationResults.isEmpty()) {
				throw new EmployeeValidationException("Errors in validating inputs", constraintValidationResults);
			}
			List<EmployeeResponse> responses = employeeService.persistEmployeeRequestEntries(validList);
			logger.info("saving data completed");
			return ResponseEntity.ok(new EmployeeListResponse(responses));
		} else {
			throw new IOException("the file to be processed is empty");
		}
	}

	@GetMapping(path = "/employees/younger/than/{age}")
	public ResponseEntity<? extends WebServiceResponse> getEmployeesYoungerThanAge(@PathVariable Integer age)
			throws EmployeeQueryException {
		logger.info("younger than");
		List<EmployeeResponse> responses = employeeService.getAllEmployeesYoungerThanAge(age);
		logger.info("younger than completed");
		return ResponseEntity.ok(new EmployeeListResponse(responses));
	}

	@GetMapping(path = "/employees/sorted/{attribute}")
	public ResponseEntity<? extends WebServiceResponse> sortEmployeesByAttribute(@PathVariable String attribute)
			throws EmployeeQueryException {
		logger.info("sorted data");
		List<EmployeeResponse> responses = employeeService.getAllEmployeesSortedByAttribute(attribute);
		logger.info("sorted data completed");
		return ResponseEntity.ok(new EmployeeListResponse(responses));
	}

}
