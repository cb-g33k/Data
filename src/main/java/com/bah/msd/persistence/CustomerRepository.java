package com.bah.msd.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.bah.msd.mcc.Customer;

@Component
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findCustomerByEmail(String email);

	Customer findByName(String name);

	void deleteByName(String name);
}