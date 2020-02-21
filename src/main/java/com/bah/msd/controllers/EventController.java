package com.bah.msd.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public void addEvent(@RequestBody Event event, UriComponentsBuilder uri, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token.replace("Bearer ", ""))) {
			eventRepo.save(event);
		}
	}
	@DeleteMapping("/{id}")
	public @ResponseBody void deleteEvent(@PathVariable Long id, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token.replace("Bearer ", ""))) {
		eventRepo.deleteById(id);
		}
	}
	@GetMapping("")
	public Iterable<Event> getEvents(@RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token.replace("Bearer ", ""))) {
			return eventRepo.findAll();
		}
		return new ArrayList<Event>();
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