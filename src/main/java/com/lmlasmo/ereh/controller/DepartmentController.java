package com.lmlasmo.ereh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.DepartmentDTO;
import com.lmlasmo.ereh.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Value("${EREH_RH_DELETE_CODE}")
	private	String deleteCode;
	
	private DepartmentService departmentService;	
	
	@Autowired
	public DepartmentController(DepartmentService departmentService){
		this.departmentService = departmentService;
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<Object> register(@RequestBody @Valid DepartmentDTO department) {
		
		department = departmentService.save(department);
		
		if(department != null) {
			return ResponseEntity.ok(department);
		}
		
		return ResponseEntity.badRequest().build();
		
	}
	
	@DeleteMapping("/delete")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<Object> delete(@RequestParam int id, @RequestParam String deleteCode){
		
		int code = (this.deleteCode.equals(deleteCode)) ? 200 : 403;
		
		if(code == 200) {
			
			code = (departmentService.existsById(id)) ? code : 404;
			
			if(code == 200) {
				code = (departmentService.deleteById(id)) ? 200 : 500;
			}
			
		}
		
		return ResponseEntity.status(code).build();		
		
	}
	
	@GetMapping(path = "/search", params = "name")	
	public ResponseEntity<DepartmentDTO> getByName(@RequestParam String name){
		
		Optional<DepartmentDTO> departmentDTO = departmentService.findByName(name.toUpperCase());
		
		if(departmentDTO.isPresent()) {
			return ResponseEntity.ok(departmentDTO.get());
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	@GetMapping(path = "/search", params = "contain")
	public ResponseEntity<Page<DepartmentDTO>> getByNameContaining(@RequestParam String contain, Pageable pageable){
		
		Page<DepartmentDTO> dtoPage = departmentService.findByNameContaining(contain, pageable);
		
		return ResponseEntity.ok(dtoPage);		
	}
	
	@GetMapping("/search")	
	public ResponseEntity<Page<DepartmentDTO>> getAll(Pageable pageable){
		
		Page<DepartmentDTO> dtoPage = departmentService.findAll(pageable);
		
		return ResponseEntity.ok(dtoPage);		
	}
	
}
