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
		
		if(entity.getId() != department.getId()) {
			return new DepartmentDTO(entity);
		}
		
		return null;
		
	}
	
	public DepartmentDTO save(DepartmentDTO departmentDTO) {
		
		Department department = new Department(departmentDTO);
		
		return save(department);
	}
	
	public DepartmentDTO findByName(String name){
		
		Optional<Department> department = departmentRepository.findByName(name);
		
		if(department.isPresent()) {
			return new DepartmentDTO(department.get());
		}
		
		return null;
		
	}
	
	public List<DepartmentDTO> findByNameContaning(String name){
		List<DepartmentDTO> dtoList = departmentRepository.findByNameContaning(name)
				.stream()
				.map(d -> new DepartmentDTO(d))
				.toList();
		
		return dtoList;
	}
	
	public Page<DepartmentDTO> findByNameContaning(String name, Pageable pageable){
		Page<DepartmentDTO> dtoPage = departmentRepository.findByNameContaning(name, pageable)				
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
