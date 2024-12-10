package com.lmlasmo.ereh.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping(name = "/search")
	public ResponseEntity<Page<EmployeeDTO>> getAll(Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findAll(pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(name = "/search", params = "email")
	public ResponseEntity<EmployeeDTO> getByEmail(@RequestParam String email){
		
		Optional<EmployeeDTO> employee = employeeService.findByEmail(email);
		
		if(employee.isPresent()) {			
			return ResponseEntity.ok(employee.get());					
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping(name = "/search", params = "id")
	public ResponseEntity<EmployeeDTO> getById(@RequestParam long id){
		
		Optional<EmployeeDTO> employee = employeeService.findByUser(id);		
		
		if(employee.isPresent()) {
			return ResponseEntity.ok(employee.get());
		}
		
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping(name = "/search", params = "name")
	public ResponseEntity<Page<EmployeeDTO>> getByName(@RequestParam String name, Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findByName(name, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);		
	}
	
	@GetMapping(name = "/search", params = "after")
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateAfter(LocalDate after, Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateAfter(after, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);		
	}
	
	@GetMapping(name = "/search", params = "before")
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateBefore(LocalDate before, Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateBefore(before, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(name = "/search", params = {"after", "before"})
	public ResponseEntity<Page<EmployeeDTO>> getByAdmissionDateBetween(LocalDate after, LocalDate before, Pageable pageable){
		
		Page<EmployeeDTO> employeePage = employeeService.findByAdmissionDateBetween(after, before, pageable);		
		employeePage.forEach(e -> e.setAddress(null));
		
		return ResponseEntity.ok(employeePage);
	}
	
	@GetMapping(name = "/exists", params = "email")
	public ResponseEntity<Object> existsByEmail(@RequestParam String email) {
		
		if(employeeService.existsByEmail(email)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@GetMapping(name = "/exists", params = "telephone")
	public ResponseEntity<Object> existsByTelephone(@RequestParam String telephone) {
		
		if(employeeService.existsByTelephone(telephone)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}
	
}
