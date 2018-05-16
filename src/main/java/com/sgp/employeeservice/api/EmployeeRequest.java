package com.sgp.employeeservice.api;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sgp.employeeservice.validator.ContactNumberConstraint;
import com.sgp.employeeservice.web.json.DateTimeDeserializer;
import com.sgp.employeeservice.web.json.DateTimeSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

	@NotNull
	private String name;
	
	@JsonProperty("joindate")
	@JsonDeserialize(using = DateTimeDeserializer.class)
	@JsonSerialize(using = DateTimeSerializer.class)
	@NotNull
	@PastOrPresent
	private Date joinDate;
	
	@Positive
	@NotNull
	@Min(value = 1)
	private Integer age;
	
	@Size(min = 1, max = 160)
	private String company;
	
	@Email
	private String email;
	
	@ContactNumberConstraint
	private String phone;
	
	@Positive
	private BigDecimal salary;
	
	@Valid
	private AddressRequest address;
}
