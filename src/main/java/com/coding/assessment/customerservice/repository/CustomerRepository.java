package com.coding.assessment.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coding.assessment.customerservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	//to fetch customers by email address
	public List<Customer> findByEmailAddress(String emailAddress);
	
	//to fetch user which has same dob, occupation and customer group
	@Query(nativeQuery = true,
			value = "select * from customer_details\r\n"
					+ "where occupation = ?1\r\n"
					+ "and customer_group = ?2\r\n"
					+ "and dob = ?3\r\n"
					+ "and flag = 0")
	public List<Customer> findByDobAndOccupationAndCustomerGroup(String occupation, String customerGroup, String dob);

	//update customer details flag
	@Transactional
	@Modifying
	@Query(nativeQuery = true,
			value = "update customer_details\r\n"
					+ "set flag = ?2\r\n"
					+ "where customer_id = ?1")
	public void updateFlagById(Long id,int flag);

	//updating customer details 
	@Transactional
	@Modifying
	@Query(nativeQuery = true,
	value = "update customer_details\r\n"
			+ "set customer_group = ?1,\r\n"
			+ "dob = ?2,\r\n"
			+ "email_address = ?3,\r\n"
			+ "first_name = ?4,\r\n"
			+ "flag = ?5,\r\n"
			+ "last_name = ?6,\r\n"
			+ "occupation = ?7\r\n"
			+ "where customer_id = ?8\r\n"
			+ "")
	public void updateCustomer(String customerGroup,String dob, String emailAddress, String firstName, int flag,
			  String lastName, String occupation, Long id);
}
