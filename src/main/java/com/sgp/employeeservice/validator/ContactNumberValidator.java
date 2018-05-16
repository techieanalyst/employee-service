package com.sgp.employeeservice.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

	@Override
	public void initialize(ContactNumberConstraint contactNumber) {

	}

	@Override
	public boolean isValid(String contactNumber, ConstraintValidatorContext context) {
		return contactNumber != null && contactNumber.matches("^[0-9]-[0-9]{3}-[0-9]{3}-[0-9]{4}$")
				&& (contactNumber.length() == 14);
	}

}
