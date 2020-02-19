package com.bah.msd.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.bah.msd.mcc.Event;

@Component
public interface EventRepository extends CrudRepository<Event, Long> {

}
