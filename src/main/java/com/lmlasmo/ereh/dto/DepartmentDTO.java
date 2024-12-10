package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Department;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDTO {
	
	@JsonProperty(required = false)
	private int id;
	
	@JsonProperty
	@NotBlank
	private String name;
	
	@JsonProperty
	@NotBlank
	private String details;
	
	public DepartmentDTO() {}
	
	public DepartmentDTO(Department department) {		
		this.setId(department.getId());
		this.setName(department.getName());
		this.setDetails(department.getDetails());		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details.toUpperCase();
	}	
	
}
