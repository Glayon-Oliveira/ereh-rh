package com.lmlasmo.ereh.dto.sign;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginDTO {

	@JsonProperty
	@NotBlank
	@Pattern(regexp =  "^[a-z0-9ñç]+$", message = "Special characters, accents or spaces are not allowed")
	private String username;
	
	@JsonProperty
	@NotBlank
	@Size(min = 8, max = 255)
	private String password;
	
	public LoginDTO() {}

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
	
}
