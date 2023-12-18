package com.coding.assessment.customerservice.exception;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.coding.assessment.customerservice.customexception.CustomerUpdationException;

//Exception Handling
@ControllerAdvice
public class ValidationException {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		// field error handing while passing json data
		if (ex.hasFieldErrors()) {
			ex.getFieldErrors().forEach(error -> {
				String fieldName = error.getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
		}

		// json object level error are caootured here
		if (ex.hasGlobalErrors()) {
			ex.getGlobalErrors().forEach(error -> {
				String fieldName = error.getObjectName();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerUpdationException.class)
	public ResponseEntity<Object> handleCustomerUpdationValidationException(CustomerUpdationException ex) {
		return new ResponseEntity<>(ex.getExceptionDetails(), HttpStatus.BAD_REQUEST);
	}

}
