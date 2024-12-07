package com.lmlasmo.ereh.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmlasmo.ereh.dto.EmployeeDTO;
import com.lmlasmo.ereh.model.Employee;
import com.lmlasmo.ereh.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public EmployeeDTO save(Employee employee) {
		
		Employee entity = employeeRepository.save(employee);
		
		if(entity.getId() != employee.getId()) {
			return new EmployeeDTO(entity);
		}
		
		return null;		
	}
	
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee(employeeDTO);
		
		return save(employee);
	}
	
	public EmployeeDTO findByName(String name) {
		
		Optional<Employee> employee = employeeRepository.findByName(name);
		
		if(employee.isPresent()) {
			return new EmployeeDTO(employee.get());
		}
		
		return null;		
	}
	
	public EmployeeDTO findByEmail(String email) {
		
		Optional<Employee> employee = employeeRepository.findByEmail(email);
		
		if(employee.isPresent()) {
			return new EmployeeDTO(employee.get());
		}
		
		return null;		
	}
	
	public List<EmployeeDTO> findByNameContaining(String name){
		
		List<EmployeeDTO> dtoList = employeeRepository.findByNameContaining(name)
				.stream()
				.map(e -> new EmployeeDTO(e))
				.toList();
		
		return dtoList;		
	}
	
	public Page<EmployeeDTO> findByNameContaining(String name, Pageable pageable){
		
		Page<EmployeeDTO> dtoPage = employeeRepository.findByNameContaining(name, pageable)
				.map(e -> new EmployeeDTO(e));				
		
		return dtoPage;
	}
	
	public List<EmployeeDTO> findByAdmissionDateAfter(LocalDate date){
		
		List<EmployeeDTO> dtoList = employeeRepository.findByAdmissionDateAfter(date)
				.stream()
				.map(e -> new EmployeeDTO(e))
				.toList();
		
		return dtoList;	
	}
	
	public Page<EmployeeDTO> findByAdmissionDateAfter(LocalDate date, Pageable pageable){
		
		Page<EmployeeDTO> dtoPage = employeeRepository.findByAdmissionDateAfter(date, pageable)
				.map(e -> new EmployeeDTO(e));				
		
		return dtoPage;
	}
	
	public List<EmployeeDTO> findByAdmissionDateBefore(LocalDate date){
	
		List<EmployeeDTO> dtoList = employeeRepository.findByAdmissionDateBefore(date)
				.stream()
				.map(e -> new EmployeeDTO(e))
				.toList();
		
		return dtoList;	
	}
	
	public Page<EmployeeDTO> findByAdmissionDateBefore(LocalDate date, Pageable pageable){
		
		Page<EmployeeDTO> dtoPage = employeeRepository.findByAdmissionDateBefore(date, pageable)
				.map(e -> new EmployeeDTO(e));				
		
		return dtoPage;
	}
	
	public List<EmployeeDTO> findByAdmissionDateBetween(LocalDate date1, LocalDate date2){
		
		List<EmployeeDTO> dtoList = employeeRepository.findByAdmissionDateBetween(date1, date2)
				.stream()
				.map(e -> new EmployeeDTO(e))
				.toList();
		
		return dtoList;	
	}
	
	public Page<EmployeeDTO> findByAdmissionDateBetween(LocalDate date1, LocalDate date2, Pageable pageable){
		
		Page<EmployeeDTO> dtoPage = employeeRepository.findByAdmissionDateBetween(date1, date2, pageable)
				.map(e -> new EmployeeDTO(e));				
		
		return dtoPage;
	}
	
	public boolean deleteById(long id) {
		employeeRepository.deleteById(id);
				
		return !existsById(id);
	}
	
	public boolean existsById(long id) {		
		return employeeRepository.existsById(id);
	}
	
	public boolean existsByEmail(String email) {
		return employeeRepository.existsByEmailIgnoreCase(email);
	}
	
	public boolean existsByTelephone(String telephone) {
		return employeeRepository.existsByTelephone(telephone);
	}
	
}
