package com.coding.assessment.customerservice.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.entity.OccupationType;
import com.coding.assessment.customerservice.service.CustomerServiceImpl;

@WebMvcTest
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerServiceImpl customerService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private CustomerDto newCustomer = new CustomerDto();

	@BeforeEach
	void setUp() throws Exception {
		
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		newCustomer.setCustomerId(1l);
		newCustomer.setDob(LocalDate.parse("1999-08-25"));
		newCustomer.setEmailAddress("deewan2581999@gmail.com");
		newCustomer.setFirstName("Deewan");
		newCustomer.setLastName("S");
		newCustomer.setOccupation(OccupationType.DEVELOPER.toString());

	}

	@Test
	@Disabled
	@WithMockUser(username = "deewan", roles ="ADMIN")
	public void whenValidCustomer_thenCustomerWillBeSaved() throws Exception {

		CustomerDto inputCustomer = new CustomerDto();

		inputCustomer.setDob(LocalDate.parse("1999-08-25"));
		inputCustomer.setEmailAddress("deewan2581999@gmail.com");
		inputCustomer.setFirstName("Deewan");
		inputCustomer.setLastName("S");
		inputCustomer.setOccupation(OccupationType.DEVELOPER.toString());

		Mockito.when(customerService.save(inputCustomer))
				.thenReturn(ResponseEntity.status(HttpStatus.OK).body(newCustomer));

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/customers")
				.header("Authorization", "Basic ZGVld2FuOkRlZXdhbkAyNQ==")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "    \"firstName\" : \"Deewan\",\r\n"
						+ "    \"lastName\" : \"S\",\r\n"
						+ "    \"emailAddress\" : \"deewan2581999@hikeon.tech\",\r\n"
						+ "    \"dob\" : \"25-08-2000\",\r\n"
						+ "    \"occupation\" : \"CHEF\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	@WithMockUser(username = "deewan", roles ="ADMIN")
	public void whenValidCustomerId_thenCustomerWillBeFetched() throws Exception {

		Mockito.when(customerService.findById(1l))
				.thenReturn(ResponseEntity.status(HttpStatus.OK).body(newCustomer));

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	@WithMockUser(username = "deewan", roles ="ADMIN")
	public void whenNotValidCustomerId_thenCustomerWillNotBeFetched() throws Exception {

		Mockito.when(customerService.findById(2l))
				.thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
	@Test
	@WithMockUser(username = "deewan", roles ="ADMIN")
	public void whenNotValidCustomerId_thenCustomerWillNotBeDeleted() throws Exception {

		Mockito.when(customerService.deleteCustomer(2l))
				.thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}
	
	@Test
	@WithMockUser(username = "deewan", roles ="ADMIN")
	public void whenValidCustomerId_thenCustomerWillNotBeDeleted() throws Exception {

		Mockito.when(customerService.deleteCustomer(2l))
				.thenReturn(ResponseEntity.status(HttpStatus.OK).build());

		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/customers/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
