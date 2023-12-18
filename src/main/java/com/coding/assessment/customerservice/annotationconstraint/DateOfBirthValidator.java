package com.coding.assessment.customerservice.annotationconstraint;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.DateOfBirth;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class DateOfBirthValidator implements ConstraintValidator<DateOfBirth, LocalDate> {

	@Override
	public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
		int age = Period.between(dob, LocalDate.now()).getYears();
		if (age >= 18)
			return true;
		return false;
	}

}
