package com.coding.assessment.customerservice.annotationconstraint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.IntegerityConstraintsDobOccupationCustomerGroup;
import com.coding.assessment.customerservice.dto.CustomerDto;
import com.coding.assessment.customerservice.entity.Customer;
import com.coding.assessment.customerservice.entity.CustomerGroupType;
import com.coding.assessment.customerservice.entity.OccupationType;
import com.coding.assessment.customerservice.service.CustomerServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IntegerityConstraintsDobOccupationCustomerGroupValidator
		implements ConstraintValidator<IntegerityConstraintsDobOccupationCustomerGroup, CustomerDto> {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Override
	public boolean isValid(CustomerDto customerDto, ConstraintValidatorContext context) {

		String dob = customerDto.getDob().toString();
		String occupation = customerDto.getOccupation();
		String customerGroup = customerServiceImpl.getCustomerGroup(customerDto).toString();

		List<Customer> listOfCustomer = customerServiceImpl.findByDobAndOccupationAndCustomerGroup(occupation,
				customerGroup, dob);

		if (listOfCustomer == null || listOfCustomer.isEmpty())
			return true;

		return false;
	}

}
