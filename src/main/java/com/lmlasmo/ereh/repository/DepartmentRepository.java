package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	public Optional<Department> findByName(String name);
	
	public List<Department> findByNameContaining(String name);
	
	public Page<Department> findByNameContaining(String name, Pageable pageable);
	
	public boolean existsByName(String name);
	
}
