package com.lmlasmo.ereh.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {
		
	@JsonProperty
	private long id;
	
	@JsonProperty
	@NotBlank
	@Pattern(regexp =  "^[\\p{L} ]+$", message = "Special characters and numbers are not allowed")
	@Size(max = 255)
	private String name;
	
	@JsonProperty	
	@NotNull
	private LocalDate birthDate;
	
	@JsonProperty
	@NotBlank
	@Email
	private String email;
		
	@JsonProperty
	@NotBlank
	@Pattern(regexp = "0-9]+$", message = "Only numbers are allowed")
	@Size(min = 10, max = 15)
	private String telephone;	
		
	@JsonProperty(required = false)
	private LocalDate admissionDate;
		
	@JsonProperty
	@NotNull
	@Valid
	private AddressDTO address;
	
	public EmployeeDTO() {}
	
	public EmployeeDTO(Employee employee) {		
		this.id = employee.getId();
		this.name = employee.getName();
		this.birthDate = employee.getBirthDate();
		this.email = employee.getEmail();
		this.telephone = employee.getTelephone();		
		this.admissionDate = employee.getAdmissionDate();
		this.address = new AddressDTO(employee.getAddress());				
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

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}	
	
}
