package com.sgp.employeeservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressResponse {

	private String street;
	private String city;
	private String zipcode;
	
}
