package com.sgp.employeeservice.api;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sgp.employeeservice.web.json.DateTimeDeserializer;
import com.sgp.employeeservice.web.json.DateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {

	private String name;
	
	@JsonProperty("joindate")
	@JsonDeserialize(using = DateTimeDeserializer.class)
	@JsonSerialize(using = DateTimeSerializer.class)
	private Date joinDate;
	
	private Integer age;
	private String company;
	private String email;
	private String phone;
	private BigDecimal salary;
	
	private AddressResponse address;	
}
