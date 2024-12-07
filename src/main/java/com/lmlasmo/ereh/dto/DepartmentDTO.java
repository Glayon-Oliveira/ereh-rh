package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Department;

public class DepartmentDTO {
	
	@JsonProperty
	private int id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String details;
	
	public DepartmentDTO() {}
	
	public DepartmentDTO(Department department) {		
		this.id = department.getId();
		this.name = department.getName();
		this.details = department.getDetails();		
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
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}	
	
}
