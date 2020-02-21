package com.bah.msd.controllers;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@PostMapping("{customerid}")
	public Customer postCustomer(@RequestBody Customer customer, UriComponentsBuilder uri, @RequestHeader("Authorization") String token) {
		Customer cust = customerRepository.save(customer);
		System.out.println(cust);
		return cust;
	}
	//@RequestHeader("Authorization") String token
	@GetMapping
	public Iterable<Customer> getCustomers(@RequestHeader("Authorization") String token) throws IOException {
	    
	    if(authenticate(token.replace("Bearer ", ""))) {
		return customerRepository.findAll();
	    }
	    return new ArrayList<Customer>();
	}

	@DeleteMapping("")
	public void deleteCustomer(@RequestBody String name, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token.replace("Bearer ", ""))) {
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
	}

	@GetMapping("/getCustomerByEmail")
	public Iterable<Customer> getCustomerByEmail(@RequestBody String email, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token.replace("Bearer ", ""))) {
		return customerRepository.findCustomerByEmail(email.replace("\"", ""));
		}
		return new ArrayList<Customer>();
	}

	@PostMapping("/byname/{name}")
	public Customer getCustomerByName(@PathVariable String name) {
		System.out.println(name);
		Customer customer = customerRepository.findByName(name);
		return customer;
	}
	
	
	private boolean authenticate(String token) throws IOException {
		String uri = "http://localhost:8080/authenticate/"+token;
		URL url = new URL(uri);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setUseCaches(false);

		int x = conn.getResponseCode();
		String y = conn.getResponseMessage();
		String inputLine = "";
		StringBuffer bufferResponse = new StringBuffer();
		BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((inputLine = resp.readLine()) != null) {
			bufferResponse.append(inputLine);
		}
		if(bufferResponse.toString().equals("true")){
			return true;
		}
		return false;
	}
}