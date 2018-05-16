package com.sgp.employeeservice.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ZipcodeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCodeConstraint {

	String message() default "Invalid zipcode format";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
