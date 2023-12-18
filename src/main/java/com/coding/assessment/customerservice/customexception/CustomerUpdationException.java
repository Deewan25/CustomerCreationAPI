package com.coding.assessment.customerservice.customexception;

import java.util.Map;

//Custom Exception to capture error while updating the customer
public class CustomerUpdationException extends Exception {

	Map<String, String> exceptionDetails;

	public CustomerUpdationException(Map<String, String> exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

	public Map<String, String> getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(Map<String, String> exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}

}
