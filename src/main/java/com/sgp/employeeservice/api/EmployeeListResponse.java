package com.sgp.employeeservice.api;

import java.util.List;

public class EmployeeListResponse extends WebServiceResponse {

	private List<EmployeeResponse> employees;

	public EmployeeListResponse(List<EmployeeResponse> employees) {
		super();
		this.employees = employees;
	}
	
	public EmployeeListResponse() {
		super();
	}

	public List<EmployeeResponse> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeResponse> employees) {
		this.employees = employees;
	}
	
}
