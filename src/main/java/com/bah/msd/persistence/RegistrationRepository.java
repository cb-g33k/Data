package com.bah.msd.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.bah.msd.mcc.Registration;

@Component
public interface RegistrationRepository extends CrudRepository<Registration, Long> {

}