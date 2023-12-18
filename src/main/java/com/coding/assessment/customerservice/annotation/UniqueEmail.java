package com.coding.assessment.customerservice.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.coding.assessment.customerservice.annotationconstraint.UniqueEmailValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


//Custom Annotation for email to prevent duplicates
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    public String message() default "Email Address should be unique";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
