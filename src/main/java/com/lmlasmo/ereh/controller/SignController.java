package com.lmlasmo.ereh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.JwtDTO;
import com.lmlasmo.ereh.dto.UserDTO;
import com.lmlasmo.ereh.dto.sign.LoginDTO;
import com.lmlasmo.ereh.dto.sign.SignupDTO;
import com.lmlasmo.ereh.service.JwtService;
import com.lmlasmo.ereh.service.UserService;

import jakarta.validation.Valid;

@RestController
public class SignController {

	private UserService userService;	
	private JwtService jwtService;
	private AuthenticationManager manager;
	private PasswordEncoder encoder;	
	
	@Autowired
	public SignController(UserService userService, JwtService jwtService, AuthenticationManager manager, PasswordEncoder encoder) {
		this.userService = userService;
		this.jwtService = jwtService;
		this.manager = manager;
		this.encoder = encoder;		
	}	
	
	@PostMapping("/login")
	public ResponseEntity<JwtDTO> login(@RequestBody @Valid LoginDTO login){
		
		Authentication auth = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		auth = manager.authenticate(auth);		
		
		String username = auth.getName();
		List<String> roles = auth.getAuthorities()
				.stream()
				.map(a -> a.getAuthority())
				.toList();
		
		String token = jwtService.gerateToken(username, roles);
		
		return ResponseEntity.ok(new JwtDTO(token));
	}
	
	@PostMapping("/signup")
	@PreAuthorize("hasAuthority('SUBADMIN_USER')")
	public ResponseEntity<UserDTO> signup(@RequestBody @Valid SignupDTO signup) {		
		
		signup.setPassword(encoder.encode(signup.getPassword()));
		
		return ResponseEntity.ok(userService.save(signup));		
	}
	
}
