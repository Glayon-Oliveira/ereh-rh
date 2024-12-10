package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Position;
import com.lmlasmo.ereh.model.RoleType;

import jakarta.validation.constraints.Min;
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
	private RoleType role;
		
	@JsonProperty
	@Min(value = 1)	
	private int department;
	
	public PositionDTO() {}
	
	public PositionDTO(Position position) {	
		this.setId(position.getId());
		this.setName(position.getName());		
		this.setRole(position.getRole().getRole());
		this.department = position.getDepartment().getId();
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
		this.name = name.toUpperCase();
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities.toUpperCase();
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}	
	
}
