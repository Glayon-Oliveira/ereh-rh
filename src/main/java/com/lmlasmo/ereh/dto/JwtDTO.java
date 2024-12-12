package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtDTO {

	@JsonProperty
	private String token; 
	
	public JwtDTO() {}
	
	public JwtDTO(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
}
