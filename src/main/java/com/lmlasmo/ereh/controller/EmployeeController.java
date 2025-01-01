package com.lmlasmo.ereh.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.EmployeeDTO;
import com.lmlasmo.ereh.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}		
	
	@GetMapping(path = "/search")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Page<EmployeeDTO>> getAll(Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findAll(pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(path = "/search", params = "email")
	@PreAuthorize("hasAuthority('GESTOR_USER') or #email.equals(authentication.principal.email)")
	public ResponseEntity<EmployeeDTO> getByEmail(@RequestParam String email){
		
		Optional<EmployeeDTO> employee = employeeService.findByEmail(email);
		
		if(employee.isPresent()) {			
			return ResponseEntity.ok(employee.get());					
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping(path = "/search", params = "id")
	@PreAuthorize("hasAuthority('GESTOR_USER') or #id == authentication.principal.id")
	public ResponseEntity<EmployeeDTO> getById(@RequestParam long id){
		
		Optional<EmployeeDTO> employee = employeeService.findById(id);		
		
		if(employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping(path = "/search", params = "user")
	@PreAuthorize("hasAuthority('GESTOR_USER') or #id == authentication.principal.id")
	public ResponseEntity<EmployeeDTO> getByUser(@RequestParam long user){
		
		Optional<EmployeeDTO> employee = employeeService.findByUser(user);		
		
		if(employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping(path = "/search", params = "name")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Page<EmployeeDTO>> getByName(@RequestParam String name, Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findByName(name, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);		
	}
	
	@GetMapping(path = "/search", params = "after")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateAfter(@RequestParam String after, Pageable pageable){
		
		LocalDate afterDate = null;
		
		try {
			afterDate =  LocalDate.parse(after);
			
			if(afterDate.isAfter(LocalDate.now())) {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}		
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateAfter(afterDate, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);		
	}
	
	@GetMapping(path = "/search", params = "before")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateBefore(@RequestParam String before, Pageable pageable){
		
		LocalDate beforeDate = null;
		
		try {
			beforeDate =  LocalDate.parse(before);			
			
			if(beforeDate.isAfter(LocalDate.now())) {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateBefore(beforeDate, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(path = "/search", params = {"after", "before"})
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateBetween(@RequestParam String after, @RequestParam String before, Pageable pageable){
		
		LocalDate afterDate = null;
		LocalDate beforeDate = null;
		
		try {
			afterDate = LocalDate.parse(after);
			beforeDate = LocalDate.parse(before);
			
			if(afterDate.isAfter(beforeDate) || afterDate.isEqual(beforeDate)) {
				return ResponseEntity.badRequest().build();
			}
			
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateBetween(afterDate, beforeDate, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(path = "/exists", params = "email")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Object> existsByEmail(@RequestParam String email) {
		
		if(employeeService.existsByEmail(email)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@GetMapping(path = "/exists", params = "telephone")
	@PreAuthorize("hasAuthority('GESTOR_USER')")
	public ResponseEntity<Object> existsByTelephone(@RequestParam String telephone) {
		
		if(employeeService.existsByTelephone(telephone)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
	
}
