package com.coding.assessment.customerservice.annotationconstraint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.Occupation;
import com.coding.assessment.customerservice.entity.OccupationType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class OccupationValidator implements ConstraintValidator<Occupation, String> {

	@Override
	public boolean isValid(String occupation, ConstraintValidatorContext context) {

		List<String> listOfOccupationType = Stream.of(OccupationType.values())
				.map(occupationValue -> occupationValue + "").collect(Collectors.toList());

		if (listOfOccupationType.contains(occupation))
			return true;

		return false;
	}

}
