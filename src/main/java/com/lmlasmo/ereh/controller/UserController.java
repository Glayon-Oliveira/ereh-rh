package com.lmlasmo.ereh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.UserDTO;
import com.lmlasmo.ereh.model.Users;
import com.lmlasmo.ereh.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Value("${EREH_RH_DELETE_CODE}")
	private String deleteCode;
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteUser(@RequestParam long id, @RequestParam String deleteCode) {
		
		if(!this.deleteCode.equals(deleteCode)) {
			return ResponseEntity.status(403).build();
		}
		
		boolean deleted = userService.deleteById(id);
		
		if(deleted) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();	
	}
	
	@GetMapping(name = "/search", params = "username")
	public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {
		
		UserDTO user = userService.findByUsername(username);
		
		if(user != null) {
			ResponseEntity.ok(user);
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable){
		
		Page<UserDTO> all = userService.findAll(pageable);
		
		return ResponseEntity.ok(all);		
	}
	
	@GetMapping(name = "/search", params = "position")
	public ResponseEntity<Page<UserDTO>> getByPosition(@RequestParam long position, Pageable pageable){
	
		Page<UserDTO> dtoPage = userService.findByPosition(position, pageable);
		
		return ResponseEntity.ok(dtoPage);
	}
	
	@GetMapping(name = "/search", params = "department")
	public ResponseEntity<Page<UserDTO>> getByDepartment(@RequestParam int department, Pageable pageable){
	
		Page<UserDTO> dtoPage = userService.findByDepartment(department, pageable);
		
		return ResponseEntity.ok(dtoPage);
	}
	
	@GetMapping(name = "/search", params = "id")
	public ResponseEntity<UserDTO> getUserById(@RequestParam long id) {
		
		UserDTO user = userService.findById(id);
		
		if(user != null) {
			ResponseEntity.ok(user);
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@GetMapping(name = "/search", params = "locked")
	public ResponseEntity<Page<UserDTO>> getLockedUser(@RequestParam boolean locked, Pageable pageable){
		
		Page<UserDTO> user = userService.findByLockedUser(locked, pageable);		
		
		return ResponseEntity.ok(user);		
	}
	
	@PutMapping("/lock")
	public ResponseEntity<UserDTO> lockUser(@RequestParam long id){
		
		UserDTO user = userService.findById(id);		
		user.setLocked(true);
		
		user = userService.save(new Users(user));
		
		return ResponseEntity.ok(user);
	}	
	
	@GetMapping("/exists")
	public ResponseEntity<Object> existsUserBy(@RequestParam long id, @RequestParam String username){
		
		if(id != 0) {
			
			if(userService.existsById(id)) {
				return ResponseEntity.ok().build();
			}
			
		}else if(username != null) {
			
			if(userService.existsByUsername(username)) {
				return ResponseEntity.ok().build();
			}
			
		}else {
			return ResponseEntity.badRequest().build();
		}
						
		return ResponseEntity.notFound().build();
	}
	
}