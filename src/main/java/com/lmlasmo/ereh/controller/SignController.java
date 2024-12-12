package com.lmlasmo.ereh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.UserDTO;
import com.lmlasmo.ereh.dto.register.SignupDTO;
import com.lmlasmo.ereh.service.UserService;

import jakarta.validation.Valid;

@RestController
public class SignController {

	private UserService userService;
	private PasswordEncoder encoder;
	
	@Autowired
	public SignController(UserService userService, PasswordEncoder encoder) {
		this.userService = userService;		
		this.encoder = encoder;
	}		
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody @Valid SignupDTO signup) {		
		
		signup.setPassword(encoder.encode(signup.getPassword()));
		
		return ResponseEntity.ok(userService.save(signup));		
	}
	
}
