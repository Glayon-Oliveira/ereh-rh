package com.lmlasmo.ereh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ereh.dto.DepartmentDTO;
import com.lmlasmo.ereh.model.Department;
import com.lmlasmo.ereh.repository.DepartmentRepository;

@Service
@Transactional
public class DepartmentService {
	
	private DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	public DepartmentDTO save(Department department) {
		
		Department entity = departmentRepository.save(department);
		
		if(entity.getId() != 0) {
			return new DepartmentDTO(entity);
		}
		
		return null;
		
	}
	
	public DepartmentDTO save(DepartmentDTO departmentDTO) {
		
		Department department = new Department(departmentDTO);
		
		return save(department);
	}
	
	public Optional<DepartmentDTO> findByName(String name){
		
		Optional<Department> department = departmentRepository.findByName(name);		
		
		return department.map(d -> new DepartmentDTO(d));		
	}
	
	public List<DepartmentDTO> findByNameContaining(String name){
		List<DepartmentDTO> dtoList = departmentRepository.findByNameContaining(name)
				.stream()
				.map(d -> new DepartmentDTO(d))
				.toList();
		
		return dtoList;
	}
	
	public Page<DepartmentDTO> findByNameContaining(String name, Pageable pageable){
		Page<DepartmentDTO> dtoPage = departmentRepository.findByNameContaining(name, pageable)				
				.map(d -> new DepartmentDTO(d));				
		
		return dtoPage;
	}
	
	public List<DepartmentDTO> findAll(){
		List<DepartmentDTO> dtoList = departmentRepository.findAll()
				.stream()
				.map(d -> new DepartmentDTO(d))
				.toList();
		
		return dtoList;
	}
	
	public Page<DepartmentDTO> findAll(Pageable pageable){
		Page<DepartmentDTO> dtoPage = departmentRepository.findAll(pageable)				
				.map(d -> new DepartmentDTO(d));				
		
		return dtoPage;
	}
	
	public boolean deleteById(int id) {
		
		departmentRepository.deleteById(id);
		
		return existsById(id);
	}
	
	public boolean existsById(int id) {
		return departmentRepository.existsById(id);
	}
	
	public boolean existsByName(String name) {
		return departmentRepository.existsByName(name.toUpperCase());
	}
	
}
