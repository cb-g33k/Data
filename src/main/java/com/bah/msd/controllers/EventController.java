package com.bah.msd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.Event;
import com.bah.msd.persistence.EventRepository;

@RestController
@RequestMapping("/api/events")
public class EventController {
	
	@Autowired
	private EventRepository eventRepo;
	
	@PutMapping("/{eventid}")
	public void addEvent(@RequestBody Event event, UriComponentsBuilder uri) {
		eventRepo.save(event);
	}
	@DeleteMapping("/{id}")
	public @ResponseBody void deleteEvent(@PathVariable Long id) {
		eventRepo.deleteById(id);
	}
	@GetMapping("")
	public Iterable<Event> getEvents() {
		return eventRepo.findAll();
	}
}
