package com.bah.msd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.Registration;
import com.bah.msd.persistence.RegistrationRepository;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationRepository registrationRepo;
	
	@PutMapping("{registrationid}")
	public void addRegistration(@RequestBody Registration registration, UriComponentsBuilder uri) {
		registrationRepo.save(registration);
	}
	@DeleteMapping("/{id}")
	public void deleteRegistration(@PathVariable Long id) {
		registrationRepo.deleteById(id);
	}
	@GetMapping("")
	public Iterable<Registration> getRegistrations(){
		Iterable<Registration> reg = registrationRepo.findAll();
		return reg;
	}
}
