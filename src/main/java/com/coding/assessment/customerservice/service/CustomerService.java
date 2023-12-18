package com.coding.assessment.customerservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coding.assessment.customerservice.customexception.CustomerUpdationException;
import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.entity.Customer;
import com.coding.assessment.customerservice.entity.CustomerGroupType;
import com.coding.assessment.customerservice.entity.OccupationType;

@Service
public interface CustomerService {

	public ResponseEntity<CustomerDto> save(CustomerDto customerDto);
	
	public List<Customer> findByEmailAddress(String emailAddress);
	
	public List<Customer> findByDobAndOccupationAndCustomerGroup(String occupation, String customerGroup, String dob);
	
	public ResponseEntity<List<CustomerDto>> findAll();
	
	public ResponseEntity<CustomerDto> updateCustomer(Long id,CustomerDto customerDto) throws CustomerUpdationException;
	
	public ResponseEntity<Object> deleteCustomer(Long id);
	
	public ResponseEntity<Object> findById(Long id);
	
	public void updateFlagById(Long id,int flag);
	
	public CustomerGroupType getCustomerGroup(CustomerDto customerDto);
	
}
