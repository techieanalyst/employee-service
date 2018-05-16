package com.sgp.employeeservice.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BasicInformation implements Serializable {
	private static final long serialVersionUID = -6445323123740941814L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private Integer age;
	
	public BasicInformation(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public BasicInformation() {
		super();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
