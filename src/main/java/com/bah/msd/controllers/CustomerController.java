package com.bah.msd.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.Customer;
import com.bah.msd.persistence.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
		
	@PutMapping("{customerid}")
	public Customer putCustomer(@RequestBody Customer customer, UriComponentsBuilder uri) {
		Customer cust = customerRepository.save(customer);
		System.out.println(cust);
		return cust;
	}
	@GetMapping
	public Iterable<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@DeleteMapping("")
	public void deleteCustomer(@RequestBody String name) {
		name = name.replace("\"", "");
		Iterable<Customer> customers = customerRepository.findAll();
		Customer target = new Customer();
		for(Customer cust : customers) {
			if(cust.getName().equals(name)) {
				target = cust;
			}
		}
		customerRepository.delete(target);
	}

	@GetMapping("/getCustomerByEmail")
	public Iterable<Customer> getCustomerByEmail(@RequestBody String email) {
		return customerRepository.findCustomerByEmail(email.replace("\"", ""));
	}

	@PostMapping("/byname/{name}")
	public Customer getCustomerByName(@PathVariable String name) {
		System.out.println(name);
		Customer customer = customerRepository.findByName(name);
		return customer;
	}
}
