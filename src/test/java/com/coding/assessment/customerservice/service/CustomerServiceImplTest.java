package com.coding.assessment.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.entity.Customer;
import com.coding.assessment.customerservice.entity.CustomerGroupType;
import com.coding.assessment.customerservice.entity.OccupationType;
import com.coding.assessment.customerservice.repository.CustomerRepository;

@SpringBootTest
class CustomerServiceImplTest {

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private Customer customer;

	@Autowired
	private CustomerDto customreDto;

	@BeforeEach
	void setUp() throws Exception {

		Customer newCustomer = new Customer();

		newCustomer.setCustomerId(1l);
		newCustomer.setDob(LocalDate.parse("1999-08-25"));
		newCustomer.setEmailAddress("deewan2581999@gmail.com");
		newCustomer.setFirstName("Deewan");
		newCustomer.setLastName("S");
		newCustomer.setOccupation(OccupationType.DEVELOPER);
		newCustomer.setCustomerGroup(CustomerGroupType.DEVELOPER);

		Mockito.when(customerRepository.save(any())).thenReturn(newCustomer);

		Optional<Customer> optionalCustomer = Optional.of(newCustomer);

		Mockito.when(customerRepository.findById(any())).thenReturn(optionalCustomer);

		doNothing().when(customerRepository).deleteById(any());

	}

	@Test
	public void whenValidCustomer_CustomerDetailsWillBeSaved() {

		customreDto.setDob(LocalDate.parse("1999-08-25"));
		customreDto.setEmailAddress("deewan2581999@gmail.com");
		customreDto.setFirstName("Deewan");
		customreDto.setLastName("S");
		customreDto.setOccupation(OccupationType.DEVELOPER.toString());

		ResponseEntity<CustomerDto> output = customerService.save(customreDto);

		assertEquals(output.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void whenValidCustomerdDelete_CustomerDetailsWillBeDeleted() {

		ResponseEntity<Object> output = customerService.deleteCustomer(1l);

		assertEquals(output.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void whenvalidCustomerId_CustomerDetailWillBeFetched() {

		ResponseEntity<Object> output = customerService.findById(1l);

		assertEquals(output.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void whenNotValidCustomerdDelete_CustomerDetailsWillBeNotDeleted() {
		
		Optional<Customer> optionalCustomer = Optional.empty();

		Mockito.when(customerRepository.findById(any())).thenReturn(optionalCustomer);

		ResponseEntity<Object> output = customerService.deleteCustomer(1l);

		assertEquals(output.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	@Test
	public void whenNotValidCustomerdId_CustomerDetailsWillBeNotFetched() {
		
		Optional<Customer> optionalCustomer = Optional.empty();

		Mockito.when(customerRepository.findById(any())).thenReturn(optionalCustomer);

		ResponseEntity<Object> output = customerService.findById(1l);

		assertEquals(output.getStatusCode(), HttpStatus.NO_CONTENT);
	}

}
