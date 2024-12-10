package com.lmlasmo.ereh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lmlasmo.ereh.model.Users;

public class UserDTO {

	@JsonProperty
	private long id;
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private boolean locked;	
	
	@JsonProperty
	private long position;
	
	public UserDTO() {}
	
	public UserDTO(Users user) {	
		this.setId(user.getId());
		this.setUsername(user.getUsername());
		this.setLocked(user.isLocked());
		this.setPosition(user.getPosition().getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}	
	
}
