package com.coding.assessment.customerservice.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.coding.assessment.customerservice.annotationconstraint.DateOfBirthValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


// Custom Annotation to check age of the customer
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = DateOfBirthValidator.class)
public @interface DateOfBirth {

	public String message() default "Age should be minimum 18";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
