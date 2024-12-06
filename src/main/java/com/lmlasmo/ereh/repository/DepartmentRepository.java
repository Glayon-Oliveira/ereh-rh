package com.lmlasmo.ereh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	public Optional<Department> findByName(String name);
	
	public List<Department> findByNameContaning(String name);
	
}
