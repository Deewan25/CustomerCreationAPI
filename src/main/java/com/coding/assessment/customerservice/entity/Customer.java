package com.coding.assessment.customerservice.entity;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.coding.assessment.customerservice.annotation.DateOfBirth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
@Entity
@Table(name = "Customer_Details", uniqueConstraints = { @UniqueConstraint(columnNames = { "dob", "occupation", "customerGroup"}),
@UniqueConstraint(columnNames = "emailAddress"),@UniqueConstraint(columnNames = "customerId")   })
public class Customer {

	@Id
	@SequenceGenerator(name = "CustomerId_Generator", sequenceName = "CustomerIdGenerator", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "CustomerIdGenerator", strategy = GenerationType.SEQUENCE)
	private Long customerId;
	@NotBlank
	@Column(nullable = false)
	private String firstName;
	@NotBlank
	@Column(nullable = false)
	private String lastName;
	@Email
	@Column(nullable = false)
	@NotBlank
	private String emailAddress;
	@NotNull
	@Column(nullable = false)
	@DateOfBirth
	private LocalDate dob;
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OccupationType occupation;
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CustomerGroupType customerGroup;
	@Column(nullable = false)
	private int flag;
	
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
	public OccupationType getOccupation() {
		return occupation;
	}
	public void setOccupation(OccupationType occupation) {
		this.occupation = occupation;
	}
	public CustomerGroupType getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(CustomerGroupType customerGroup) {
		this.customerGroup = customerGroup;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", dob=" + dob + ", occupation=" + occupation + ", customerGroup="
				+ customerGroup + ", flag=" + flag + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerGroup, customerId, dob, emailAddress, firstName, flag, lastName, occupation);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return customerGroup == other.customerGroup && Objects.equals(customerId, other.customerId)
				&& Objects.equals(dob, other.dob) && Objects.equals(emailAddress, other.emailAddress)
				&& Objects.equals(firstName, other.firstName) && flag == other.flag
				&& Objects.equals(lastName, other.lastName) && occupation == other.occupation;
	}
	
	public Customer(@NotNull Long customerId, @NotBlank String firstName, @NotBlank String lastName,
			@Email @NotBlank String emailAddress, @NotNull LocalDate dob, @NotNull OccupationType occupation,
			@NotNull CustomerGroupType customerGroup, int flag) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.dob = dob;
		this.occupation = occupation;
		this.customerGroup = customerGroup;
		this.flag = flag;
	}

	
	public Customer() {
		
	}

}
