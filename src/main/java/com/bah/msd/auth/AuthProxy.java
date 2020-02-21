package com.bah.msd.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bah.msd.mcc.Customer;


@RestController
@RequestMapping("")
public class AuthProxy{
	private static String token;
	private final String url = "http://localhost:8080";
	public AuthProxy() {}
	
	@PostMapping("/api/token/{token}")
	public String setToken(@PathVariable String token) {
		AuthProxy.token = token;
		return AuthProxy.token;
	}
	@GetMapping("/api/token")
	public String getToken() {
		return AuthProxy.token;
	}
	@PostMapping("/authenticate/{token}")
	public boolean authenticate(@PathVariable String token) {
		String tok = this.getToken();
		if(this.getToken().contentEquals(token.replace("Bearer ", "").replace(" ", ""))) {
			return true;
		}
		return false;
	}
	
	@PostMapping("/account/register")
	public String putCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponent) throws IOException {
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
	
	
}