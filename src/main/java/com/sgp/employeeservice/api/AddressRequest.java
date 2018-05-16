package com.sgp.employeeservice.api;

import javax.validation.constraints.NotNull;

import com.sgp.employeeservice.validator.ZipCodeConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

	@NotNull
	private String street;

	@NotNull
	private String city;

	@ZipCodeConstraint
	private String zipcode;
}
