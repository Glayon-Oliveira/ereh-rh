package com.lmlasmo.ereh.model;

import java.util.HashSet;
import java.util.Set;

import com.lmlasmo.ereh.dto.PositionDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "position")
public class Position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(columnDefinition = "TEXT")
	private String activities;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Roles role;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Department department;
	
	@OneToMany(mappedBy = "position", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)	
	private Set<Users> users = new HashSet<>();
	
	public Position() {}
	
	public Position(PositionDTO positionDTO) {		
		this.id = positionDTO.getId();
		this.name = positionDTO.getName();
		this.activities = positionDTO.getActivities();
		this.role = new Roles(positionDTO.getRole());			
		this.department = new Department();
		this.department.setId(positionDTO.getDepartment());
	}
	
	@PrePersist
	@PreUpdate
	private void toUpperFields() {		
		name = name.toUpperCase();
		activities = activities.toUpperCase();		
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}	
	
}
