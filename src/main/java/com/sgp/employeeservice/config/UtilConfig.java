package com.sgp.employeeservice.config;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

	@Bean
	public Validator validator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
}
