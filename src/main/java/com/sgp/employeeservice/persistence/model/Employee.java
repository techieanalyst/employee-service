package com.sgp.employeeservice.persistence.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

	@EmbeddedId
	private BasicInformation basicInformation;

	@Column(name = "join_date")
	private Date joinDate;
	private String company;

	private BigDecimal salary;

	private String street;

	private String city;

	private String zipcode;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "phone", nullable = true)
	private String phone;

}
