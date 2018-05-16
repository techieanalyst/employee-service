package com.sgp.employeeservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void fillUploadFilesWithoutErrors() throws Exception {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("integration/employee.json");
		Reader reader = new InputStreamReader(in);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "employee.json", "text/plain", IOUtils.toByteArray(reader, "UTF-16"));
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/v1/employees/bulkupload")
				.file(mockMultipartFile);
		mockMvc.perform(builder).andExpect(status().isOk())
				.andExpect(jsonPath("employees").isNotEmpty())
				.andExpect(jsonPath("employees[0].address.city").value("Verrayes"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void filterEmployeesByAttributeAge() throws Exception {
		mockMvc.perform(get("/v1/employees/younger/than/{age}", "25").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("employees").isArray())
				.andExpect(jsonPath("employees.*", org.hamcrest.Matchers.hasSize(23)));
	}
	
	@Test
	public void sortEmployeesByAttributeSalary() throws Exception {
		mockMvc.perform(get("/v1/employees/sorted/{attribute}", "salary").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("employees").isArray())
				.andExpect(jsonPath("employees.*", org.hamcrest.Matchers.hasSize(100)))
				.andExpect(jsonPath("employees[0].salary").value("1116.0"))
				.andExpect(jsonPath("employees[0].name").value("Cameron"))
				.andExpect(jsonPath("employees[99].salary").value("9934.0"))
				.andExpect(jsonPath("employees[99].name").value("Lucian"));
	}

}
