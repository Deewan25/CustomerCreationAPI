package com.coding.assessment.customerservice.dto;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.DateOfBirth;
import com.coding.assessment.customerservice.annotation.IntegerityConstraintsDobOccupationCustomerGroup;
import com.coding.assessment.customerservice.annotation.Occupation;
import com.coding.assessment.customerservice.annotation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@IntegerityConstraintsDobOccupationCustomerGroup
public class CustomerDto {

	
	private Long customerId;
	@NotBlank(message = "First Name cannot be blank")
	private String firstName;
	@NotBlank(message = "Last Name cannot be blank")
	private String lastName;
	@UniqueEmail
	@NotBlank(message = "Email Address cannot be blank")
	@Email
	private String emailAddress;
	@NotNull(message = "Date of Birth cannot be blank")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateOfBirth(message = "Age should be minimum 18")
	private LocalDate dob;
	@NotNull
	@Occupation(message = "It accepts only these values DEVELOPER, CHEF, PLUMBER, CARPENTER, OTHER")
	private String occupation;
	

	public CustomerDto(Long customerId, @NotBlank(message = "First Name cannot be blank") String firstName,
			@NotBlank(message = "Last Name cannot be blank") String lastName,
			@NotBlank(message = "Email Address cannot be blank") @Email String emailAddress,
			@NotNull(message = "Date of Birth cannot be blank") LocalDate dob, @NotNull String occupation) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.dob = dob;
		this.occupation = occupation;
	}
	
	
	
	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmailAddress() {
		return emailAddress;
	}



	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public String getOccupation() {
		return occupation;
	}



	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}



	@Override
	public int hashCode() {
		return Objects.hash(customerId, dob, emailAddress, firstName, lastName, occupation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(dob, other.dob)
				&& Objects.equals(emailAddress, other.emailAddress) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(occupation, other.occupation);
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", dob=" + dob + ", occupation=" + occupation + "]";
	}

	public CustomerDto() {
		
	}
	
}
