package com.bah.msd.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
import com.bah.msd.mcc.Event;
import com.bah.msd.mcc.Registration;


@RestController
@RequestMapping("/api/proxy")
public class AuthProxy{
	private String token;
	private final String url = "http://localhost:8080";
	
	@PostMapping("/token/{token}")
	public void setToken(@PathVariable String token) {
		this.token = token;
	}
	@GetMapping("/token")
	public String getToken() {
		return this.token;
	}

	private boolean authenticate(String token) {
		if(this.getToken().contentEquals(token.replace("Bearer ", ""))) {
			return true;
		}
		return false;
	}
	
	@PutMapping("/customers")
	public String putCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponent, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token)){
			customer.setId(0l);
			String uri = "http://localhost:8080/api/customers/0";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setUseCaches(false);
			
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			
			os.writeChars("{\"id\":" +customer.getId()+
					", \"name\":"+ "\""+customer.getName()+"\""+
					", \"email\":" +"\""+customer.getEmail()+"\""+
					", \"password\":"+"\""+customer.getPassword()+"\""+"}");
			os.close();

			int x = conn.getResponseCode();
			String y = conn.getResponseMessage();
			String inputLine = "";
			StringBuffer bufferResponse = new StringBuffer();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = resp.readLine()) != null) {
				bufferResponse.append(inputLine);
			}
			return bufferResponse.toString();
			
		}
		return null;
	}
	
	@GetMapping("/customers")
	public String getCustomers(@RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token)){
			String uri = url + "/api/customers";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setUseCaches(false);
			int x = conn.getResponseCode();
			
			String inputLine = "";
			StringBuffer bufferResponse = new StringBuffer();
			
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = resp.readLine()) != null) {
				bufferResponse.append(inputLine);
			}
			return bufferResponse.toString();
		}
		return null;
	}
	
	@DeleteMapping("/customers")
	public String deleteCustomer(@RequestBody String name, @RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token)){
			String uri = url + "/api/customers";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setUseCaches(false);
			
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			
			
			os.writeBytes(name);
			os.close();

			int x = conn.getResponseCode();
			String y = conn.getResponseMessage();
			String inputLine = "";
			StringBuffer bufferResponse = new StringBuffer();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = resp.readLine()) != null) {
				bufferResponse.append(inputLine);
			}
			return bufferResponse.toString();
			
		}
		return null;
	}
	
	@GetMapping("/events")
	public String getEvents(@RequestHeader("Authorization") String token) throws IOException {
		if(authenticate(token)){
			String uri = url + "/api/events";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setUseCaches(false);
			int x = conn.getResponseCode();
			
			String inputLine = "";
			StringBuffer bufferResponse = new StringBuffer();
			
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = resp.readLine()) != null) {
				bufferResponse.append(inputLine);
			}
			return bufferResponse.toString();
		}
		return null;
	}
}
