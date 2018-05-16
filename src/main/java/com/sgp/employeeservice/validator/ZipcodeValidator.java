package com.sgp.employeeservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ZipcodeValidator implements ConstraintValidator<ZipCodeConstraint, String> {

	@Override
	public void initialize(ZipCodeConstraint zipCode) {

	}

	@Override
	public boolean isValid(String zipCode, ConstraintValidatorContext context) {
		return zipCode != null && (zipCode.matches("([0-9]){4,5}") || zipCode.matches("([0-9A-Z]){3} ([0-9A-Z]){3}")
				|| zipCode.matches("([0-9A-Z]){3}") || zipCode.matches("[0-9]{5}-[0-9]{3}"));
	}

}
