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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

}
