package com.lmlasmo.ereh.dto.sign;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.dto.EmployeeDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupDTO {

	@JsonProperty
	@NotBlank
	@Pattern(regexp =  "^[a-z0-9ñç]+$", message = "Special characters, accents or spaces are not allowed")
	@Size(min = 2 ,max = 255)
	private String username;
		
	@JsonProperty
	@NotBlank
	@Size(min = 8, max = 255)
	private String password;
	
	@JsonProperty
	@Min(value = 1)
	private long position;	
	
	@JsonProperty
	@NotNull
	private EmployeeDTO employee;
		
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

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}
	
}
