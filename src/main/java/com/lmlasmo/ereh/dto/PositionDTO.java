package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Position;
import com.lmlasmo.ereh.model.Roles;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PositionDTO {
		
	@JsonProperty
	private long id;
	
	@JsonProperty
	@NotBlank
	private String name;
	
	@JsonProperty
	@NotBlank
	private String activities;
	
	@JsonProperty
	@NotNull
	private Roles role;
		
	@JsonProperty
	@NotNull
	@Valid
	private DepartmentDTO department;
	
	public PositionDTO() {}
	
	public PositionDTO(Position position) {	
		this.id = position.getId();
		this.name = position.getName();		
		this.role = position.getRole();
		this.department = new DepartmentDTO(position.getDepartment());		
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

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentDTO department) {
		this.department = department;
	}	
	
}
