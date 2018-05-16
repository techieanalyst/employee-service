package com.sgp.employeeservice.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiError extends WebServiceResponse {
	
	private HttpStatus status;
	private String message;
	private List<String> errors;
	
	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}
	
	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	
}
