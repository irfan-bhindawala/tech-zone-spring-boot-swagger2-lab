package com.techzone.springbootswagger2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techzone.springbootswagger2.entities.Customer;
import com.techzone.springbootswagger2.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(final int id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer saveCustomer(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(final Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomerById(Integer id) {
		customerRepository.deleteById(id);
		
	}

}
