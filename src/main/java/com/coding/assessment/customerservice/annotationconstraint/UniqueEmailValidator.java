package com.coding.assessment.customerservice.annotationconstraint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.UniqueEmail;
import com.coding.assessment.customerservice.entity.Customer;
import com.coding.assessment.customerservice.service.CustomerServiceImpl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Override
	public boolean isValid(String emailAddress, ConstraintValidatorContext context) {

		List<Customer> listOfCustomer = customerServiceImpl.findByEmailAddress(emailAddress);
		if (listOfCustomer == null || listOfCustomer.isEmpty())
			return true;

		return false;
	}

}
