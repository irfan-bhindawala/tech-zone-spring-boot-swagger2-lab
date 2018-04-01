package com.techzone.springbootswagger2.services;

import java.util.List;

import com.techzone.springbootswagger2.entities.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomers();

	Customer getCustomerById(final int id);

	Customer saveCustomer(final Customer customer);

	Customer updateCustomer(final Customer customer);

	void deleteCustomer(final Customer customer);

	void deleteCustomerById(final Integer id);

}
