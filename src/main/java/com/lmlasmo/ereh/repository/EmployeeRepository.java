package com.lmlasmo.ereh.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmlasmo.ereh.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	public Optional<Employee> findByName(String name);
	
	public Optional<Employee> findByEmail(String name);
	
	public List<Employee> findByNameContaining(String name);
	
	public List<Employee> findBySalaryGreaterThan(float salary);
	
	public List<Employee> findBySalaryLessThan(float salary);
	
	public List<Employee> findBySalaryBetween(float salary1, float salary2);
	
	public List<Employee> findByAdmissionDateAfter(LocalDate date);
	
	public List<Employee> findByAdmissionDateBefore(LocalDate date);
	
	public List<Employee> findByAdmissionDateBetween(LocalDate date1, LocalDate date2);	
	
}
