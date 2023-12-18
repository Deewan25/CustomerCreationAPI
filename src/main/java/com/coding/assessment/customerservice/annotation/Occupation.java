package com.coding.assessment.customerservice.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.coding.assessment.customerservice.annotationconstraint.OccupationValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


//Custom Annotation to validate occupation type
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = OccupationValidator.class)
public @interface Occupation {
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public String message();
}

