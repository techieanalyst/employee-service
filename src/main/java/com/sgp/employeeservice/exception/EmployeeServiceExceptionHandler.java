package com.sgp.employeeservice.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sgp.employeeservice.api.ApiError;
import com.sgp.employeeservice.api.EmployeeRequest;
import com.sgp.employeeservice.api.ValidList;
import com.sgp.employeeservice.api.WebServiceResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class EmployeeServiceExceptionHandler {

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({HttpMessageNotReadableException.class, JsonMappingException.class, JsonParseException.class, IOException.class})
	public ResponseEntity<? extends WebServiceResponse> handleBadRequest(final Exception e) {
//		log.error("Error occurred while processing the request", e);
		ApiError apiError = new ApiError(BAD_REQUEST, "Error occurred due to invalid request", "See logs for more details");
		return ResponseEntity.badRequest().body(apiError);
	}
	
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({EmployeeQueryException.class})
	public ResponseEntity<? extends WebServiceResponse> handleEmployeeQueryException(final EmployeeQueryException e) {
//		log.error("Error occurred while processing the request", e);
		ApiError apiError = new ApiError(BAD_REQUEST, "Error occurred due to invalid request", e.getLocalizedMessage());
		return ResponseEntity.badRequest().body(apiError);
	}
	
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({MaxUploadSizeExceededException.class})
	public ResponseEntity<? extends WebServiceResponse> handleMaxUploadSizeExceededException(final MaxUploadSizeExceededException e) {
//		log.error("Error occurred while processing the request", e);
		ApiError apiError = new ApiError(BAD_REQUEST, "Error occurred due to invalid request", e.getLocalizedMessage());
		return ResponseEntity.badRequest().body(apiError);
	}
	
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<? extends WebServiceResponse> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
//		log.error("Error occurred while processing the request", e);
		ApiError apiError = new ApiError(BAD_REQUEST, "Error occurred due to invalid request", e.getLocalizedMessage());
		return ResponseEntity.badRequest().body(apiError);
	}
	
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler({InvalidDataAccessResourceUsageException.class, DataIntegrityViolationException.class})
	public ResponseEntity<? extends WebServiceResponse> handleInternalServerErrorExceptions(final MethodArgumentTypeMismatchException e) {
//		log.error("Error occurred while processing the request", e);
		ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR, "Error occurred due to invalid request", "See logs for more details");
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(apiError);
	}
	
	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class, EmployeeValidationException.class})
	public ResponseEntity<? extends WebServiceResponse> handleConstraintValidationException(final Exception ex) {
//		log.error("Error occurred while processing the request", e);
		List<String> errors = new ArrayList<String>();
		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
			for (FieldError error : e.getBindingResult().getFieldErrors()) {
				errors.add(error.getField() + ": " + error.getDefaultMessage());
			}
			for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
				errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
			}
		} else if (ex instanceof EmployeeValidationException) {
			EmployeeValidationException e = (EmployeeValidationException) ex;
			for (ConstraintViolation<ValidList<EmployeeRequest>> error : e.getValidations()) {
				errors.add(error.getPropertyPath() + ": " + error.getMessage());
			}
		}
		ApiError apiError = new ApiError(BAD_REQUEST, "Errors on validating JSON input data", errors);
		return ResponseEntity.badRequest().body(apiError);
	}
}
