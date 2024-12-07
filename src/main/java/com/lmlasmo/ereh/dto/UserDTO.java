package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Users;

public class UserDTO {

	@JsonProperty
	private String id;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private boolean locked;
	
	@JsonProperty
	private PositionDTO position;
	
	@JsonProperty
	private EmployeeDTO employee;
	
	public UserDTO() {}
	
	public UserDTO(Users user) {	
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setLocked(user.isLocked());
		this.position = new PositionDTO(user.getPosition());
		this.employee = new EmployeeDTO(user.getEmployee());		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toLowerCase();
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}		
	
}
