package com.lmlasmo.ereh.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority{

	private static final long serialVersionUID = 3153416668934102560L;	
	
	@Id
	@Enumerated(EnumType.STRING)	
	private RoleType role;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)	
	private Set<Position> position = new HashSet<>();
	
	public Roles() {}
	
	public Roles(RoleType role) {
		this.role = role;
	}
	
	@Override
	public String getAuthority() {
		return role.name();
	}
	
	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Set<Position> getPosition() {
		return position;
	}

	public void setPosition(Set<Position> position) {
		this.position = position;
	}
	
}
