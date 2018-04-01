package com.techzone.springbootswagger2.restendpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techzone.springbootswagger2.entities.Customer;
import com.techzone.springbootswagger2.services.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "customer crud endpoints", produces ="application/jason", consumes = "applicatoin/xml")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "retrieve all the customers")
	@GetMapping("/all")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers;
		customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@ApiOperation(value = "retrieve customer by providing customer id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successfully found the customer with the provided id"), 
			@ApiResponse(code = 404, message = "no resource exist for the provided data") 
		})
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Integer id) {
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@ApiOperation(value = "create new customer")
	@PostMapping()
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer persistedCustomer = customerService.saveCustomer(customer);
		return new ResponseEntity<>(persistedCustomer, HttpStatus.CREATED);
	}

	@ApiOperation(value = "update the existing customer")
	@PutMapping
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer mergedCustomer = customerService.updateCustomer(customer);
		return new ResponseEntity<>(mergedCustomer, HttpStatus.OK);
	}

	@ApiOperation(value = "delete the customer")
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteCustomer(@RequestBody Customer customer) {
		customerService.deleteCustomer(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "delete the customer with the customer id")
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable(value = "id") Integer id) {
		customerService.deleteCustomerById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
