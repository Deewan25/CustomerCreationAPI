package com.coding.assessment.customerservice.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.coding.assessment.customerservice.annotationconstraint.IntegerityConstraintsDobOccupationCustomerGroupValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

//Custom Annotation to validate sql integrity constraints of dob, occupation and customer group.
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = IntegerityConstraintsDobOccupationCustomerGroupValidator.class)
public @interface IntegerityConstraintsDobOccupationCustomerGroup {

	public String message() default "Dob and Occupation combination already exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
