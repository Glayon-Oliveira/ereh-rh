package com.lmlasmo.ereh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	public Optional<Employee> findByEmail(String email);
	
	public Optional<Employee> findByUserId(long id);
	
	public List<Employee> findByNameContaining(String name);
	
	public Page<Employee> findByNameContaining(String name, Pageable pageable);
	
	public List<Employee> findByAdmissionDateAfter(LocalDate date);
	
	public Page<Employee> findByAdmissionDateAfter(LocalDate date, Pageable pageable);
	
	public List<Employee> findByAdmissionDateBefore(LocalDate date);
	
	public Page<Employee> findByAdmissionDateBefore(LocalDate date, Pageable pageable);
	
	public List<Employee> findByAdmissionDateBetween(LocalDate date1, LocalDate date2);
	
	public Page<Employee> findByAdmissionDateBetween(LocalDate date1, LocalDate date2, Pageable pageable);
	
	public boolean existsByEmail(String email);
	
	public boolean existsByTelephone(String telephone);
	
}
