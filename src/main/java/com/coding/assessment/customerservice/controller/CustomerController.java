package com.coding.assessment.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding.assessment.customerservice.customexception.CustomerUpdationException;
import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.service.CustomerService;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// Post request for new customer creation
	@PostMapping(value = "/v1/customers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerDto> saveCustomer(@RequestBody @Valid CustomerDto customerDto) {
		return customerService.save(customerDto);
	}

	// Get request for fetching all the customers
	@GetMapping(value = "/v1/customers", produces = "application/json")
	public ResponseEntity<List<CustomerDto>> fetchCustomer() {
		return customerService.findAll();
	}

	// Get request for fetching the requested customer
	@GetMapping(value = "v1/customers/{id}", produces = "application/json")
	public ResponseEntity<Object> fetchCustomerById(@PathVariable Long id) {
		return customerService.findById(id);
	}

	// Put request for updating the existing customer details
	@PutMapping(value = "/v1/customers/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto)
			throws CustomerUpdationException {
		return customerService.updateCustomer(id, customerDto);
	}

	// Delete request for deleting the existing customer
	@DeleteMapping(value = "/v1/customers/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}
}
