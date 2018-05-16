package com.sgp.employeeservice.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.sgp.employeeservice.api.EmployeeRequest;
import com.sgp.employeeservice.api.ValidList;

public class EmployeeValidationException extends Exception {

	private static final long serialVersionUID = 3717300123895668797L;
	private Set<ConstraintViolation<ValidList<EmployeeRequest>>> validations;
	
	public EmployeeValidationException(String message, Set<ConstraintViolation<ValidList<EmployeeRequest>>> validations) {
		super(message);
		this.validations = validations;
	}

	public Set<ConstraintViolation<ValidList<EmployeeRequest>>> getValidations() {
		return validations;
	}
	
}
