package com.bah.msd.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bah.msd.mcc.Token;

@RestController
@RequestMapping("/api/token")
public class UserAuthentication {
	@GetMapping()
	public Token getToken(){
		return null;
		//return tokenapi call
	}
	@PostMapping()
	public void setToken(Token token) {
		
	}
}
