package com.lmlasmo.ereh.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.dto.EmployeeDTO;
import com.lmlasmo.ereh.dto.PositionDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupDTO {

	@JsonProperty
	@NotBlank
	@Size(min = 2 ,max = 255)
	private String username;
		
	@JsonProperty
	@NotBlank
	@Size(min = 8, max = 255)
	private String password;
	
	@JsonProperty
	@NotNull
	private EmployeeDTO employee;
	
	@JsonProperty
	@NotNull
	private PositionDTO position;
	
	public SignupDTO() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}		
	
}
