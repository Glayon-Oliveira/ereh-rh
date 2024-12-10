package com.lmlasmo.ereh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmlasmo.ereh.dto.PositionDTO;
import com.lmlasmo.ereh.service.PositionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/position")
public class PositionController {
	
	@Value("${EREH_RH_DELETE_CODE}")
	private String deleteCode;
	
	private PositionService positionService;
	
	@Autowired
	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@PostMapping("/register")
	public ResponseEntity<PositionDTO> register(@RequestBody @Valid PositionDTO position){
		
		PositionDTO newPosition = positionService.save(position);
		
		if(newPosition != null) {
			return ResponseEntity.ok(newPosition);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@RequestParam int id, @RequestParam String deleteCode){

		int code = (this.deleteCode.equals(deleteCode)) ? 200 : 403;
		
		if(code == 200) {
			
			code = (positionService.existsById(id)) ? code : 404;
			
			if(code == 200) {
				code = (positionService.deleteById(id)) ? 200 : 500;
			}
			
		}
		
		return ResponseEntity.status(code).build();	

	}
		
	@GetMapping(name = "/search", params = "name")
	public ResponseEntity<PositionDTO> getByName(@RequestParam String name){
		
		PositionDTO positionDTO = positionService.findByName(name.toUpperCase());
		
		if(positionDTO != null) {
			return ResponseEntity.ok(positionDTO);
		}
		
		return ResponseEntity.notFound().build();	
	}
		
	@GetMapping(name = "/search", params = "contain")
	public ResponseEntity<Page<PositionDTO>> getByNameContaning(@RequestParam String contain, Pageable pageable){
		
		Page<PositionDTO> dtoPage = positionService.findByNameContaining(contain, pageable);
		
		return ResponseEntity.ok(dtoPage);		
	}
	
	@GetMapping(name = "/search")
	public ResponseEntity<Page<PositionDTO>> getAll(Pageable pageable){
		
		Page<PositionDTO> dtoPage = positionService.findAll(pageable);
		
		return ResponseEntity.ok(dtoPage);		
	}
	
	@GetMapping(name = "search", params = "department")
	public ResponseEntity<Page<PositionDTO>> getByDepartment(@RequestParam int department, Pageable pageable){
		
		Page<PositionDTO> dtoPage = positionService.findByDepartmentId(department, pageable);
		
		return ResponseEntity.ok(dtoPage);		
	}
	
}