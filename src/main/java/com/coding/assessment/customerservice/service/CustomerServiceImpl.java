package com.coding.assessment.customerservice.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coding.assessment.customerservice.customexception.CustomerUpdationException;
import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.entity.Customer;
import com.coding.assessment.customerservice.entity.CustomerGroupType;
import com.coding.assessment.customerservice.entity.OccupationType;
import com.coding.assessment.customerservice.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	// saving the customer details
	public ResponseEntity<CustomerDto> save(CustomerDto customerDto) {

		CustomerGroupType customerGroup = getCustomerGroup(customerDto);

		Customer customer = modelMapper.map(customerDto, Customer.class);

		customer.setCustomerGroup(customerGroup);

		customer = customerRepository.save(customer);

		customerDto = modelMapper.map(customer, CustomerDto.class);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	// calculating the customer group based on the email and occupation type
	public CustomerGroupType getCustomerGroup(CustomerDto customerDto) {
		if (customerDto.getEmailAddress().contains("@hikeon.tech")) {
			return CustomerGroupType.HIKEON;
		} else {
			if (customerDto.getOccupation().equals("DEVELOPER")) {
				return CustomerGroupType.DEVELOPER;
			} else if (customerDto.getOccupation().equals("CHEF")) {
				return CustomerGroupType.CHEF;
			} else {
				return CustomerGroupType.NA;
			}
		}
	}

	// fetching customers by email address
	@Override
	public List<Customer> findByEmailAddress(String emailAddress) {

		return customerRepository.findByEmailAddress(emailAddress);
	}

	// fetch customer who has same dob, occupation and customer group
	@Override
	public List<Customer> findByDobAndOccupationAndCustomerGroup(String occupation, String customerGroup, String dob) {

		return customerRepository.findByDobAndOccupationAndCustomerGroup(occupation, customerGroup, dob);
	}

	// fetch all the customers
	public ResponseEntity<List<CustomerDto>> findAll() {

		List<CustomerDto> listOfCustomerDtos = new ArrayList<>();
		List<Customer> listOfCustomers = customerRepository.findAll();

		for (Customer customer : listOfCustomers)
			listOfCustomerDtos.add(modelMapper.map(customer, CustomerDto.class));
		if (listOfCustomerDtos == null || listOfCustomerDtos.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.status(HttpStatus.OK).body(listOfCustomerDtos);
	}

	// update the customer details
	public ResponseEntity<CustomerDto> updateCustomer(Long id, CustomerDto customerDto)
			throws CustomerUpdationException {

		Customer customer = customerRepository.findById(id).get();

		// set the flag before updating the customer
		updateFlagById(id, 1);

		Map<String, String> exceptionMap = new HashMap<>();

		if (customer == null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		else {

			if (customerDto.getFirstName() == null || customerDto.getFirstName().isBlank()) {
				exceptionMap.put("firstName", "First name is missing");
			}
			if (customerDto.getLastName() == null || customerDto.getLastName().isBlank()) {
				exceptionMap.put("lastName", "Last name is missing");
			}
			List<String> listOfOccupation = Stream.of(OccupationType.values()).map(occupation -> occupation + "")
					.toList();
			if (customerDto.getOccupation() == null || !listOfOccupation.contains(customerDto.getOccupation())
					|| customerDto.getLastName().isBlank()) {
				exceptionMap.put("occupation", "check the occupation");
			}
			if (Period.between(customerDto.getDob(), LocalDate.now()).getYears() < 18) {
				exceptionMap.put("dob", "Age should be minimum 18");
			}
			if (findByEmailAddress(customerDto.getEmailAddress()) != null
					&& findByEmailAddress(customerDto.getEmailAddress()).size() > 0) {

				exceptionMap.put("emailAddress", "Email Address exists already");

			}
			if (findByDobAndOccupationAndCustomerGroup(customerDto.getOccupation().toString(),
					getCustomerGroup(customerDto).toString(), customerDto.getDob().toString()) != null
					&& findByDobAndOccupationAndCustomerGroup(customerDto.getOccupation().toString(),
							getCustomerGroup(customerDto).toString(), customerDto.getDob().toString()).size() > 0) {

				exceptionMap.put("customerDto", "Dob and Occupation combination already exists");
			}
			if (exceptionMap.size() > 0) {
				save(modelMapper.map(customer, CustomerDto.class));
				// rollback the flag for customers
				updateFlagById(id, 0);
				throw new CustomerUpdationException(exceptionMap);
			}
		}
		customer = modelMapper.map(customerDto, Customer.class);
		customer.setCustomerId(id);
		customer.setFlag(0);
		customer.setCustomerGroup(getCustomerGroup(customerDto));

		// updating the customer
		customerRepository.updateCustomer(customer.getCustomerGroup().toString(), customer.getDob().toString(),
				customer.getEmailAddress(), customer.getFirstName(), customer.getFlag(), customer.getLastName(),
				customer.getOccupation().toString(), customer.getCustomerId());

		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(customer, CustomerDto.class));
	}

	// deleting the customer by id
	public ResponseEntity<Object> deleteCustomer(Long id) {

		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		customerRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted the Customer");
	}

	// fetch the customer by id
	public ResponseEntity<Object> findById(Long id) {

		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(customer, CustomerDto.class));
	}

	// flag update for customer
	@Override
	public void updateFlagById(Long id, int flag) {
		customerRepository.updateFlagById(id, flag);

	}

}
