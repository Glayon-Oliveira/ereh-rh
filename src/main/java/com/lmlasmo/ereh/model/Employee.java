package com.lmlasmo.ereh.model;

import java.time.LocalDate;

import com.lmlasmo.ereh.dto.EmployeeDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {	
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;
	
	@Column(name =  "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false, unique = true)
	private String telephone;	
	
	@Column(name = "admission_date", nullable = false)
	private LocalDate admissionDate;
	
	@Embedded
	private Address address;
	
	@OneToOne(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Users user;
	
	@PrePersist
	@PreUpdate
	private void toUpperFields() {		
		name = name.toUpperCase();	
		email = email.toLowerCase();
	}
	
	public Employee() {}
	
	public Employee(EmployeeDTO employeeDTO) {
		this.id = employeeDTO.getId();
		this.name = employeeDTO.getName();
		this.birthDate = employeeDTO.getBirthDate();
		this.email = employeeDTO.getEmail();
		this.telephone = employeeDTO.getTelephone();
		this.admissionDate = employeeDTO.getAdmissionDate();
		this.address = new Address(employeeDTO.getAddress());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}	
	
}
