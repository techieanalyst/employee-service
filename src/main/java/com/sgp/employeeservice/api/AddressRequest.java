package com.sgp.employeeservice.api;

import javax.validation.constraints.NotNull;

import com.sgp.employeeservice.validator.ZipCodeConstraint;

public class AddressRequest {

	@NotNull
	private String street;

	@NotNull
	private String city;

	@ZipCodeConstraint
	@NotNull
	private String zipcode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}