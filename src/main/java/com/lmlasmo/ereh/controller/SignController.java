package com.lmlasmo.ereh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	public SignController(UserService userService) {
		this.userService = userService;		
	}	
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signup(@RequestBody @Valid SignupDTO signup) {		
		return ResponseEntity.ok(userService.save(signup));		
	}
	
}
