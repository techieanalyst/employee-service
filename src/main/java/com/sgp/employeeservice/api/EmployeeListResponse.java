package com.sgp.employeeservice.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponse extends WebServiceResponse {

	private List<EmployeeResponse> employees;
}
