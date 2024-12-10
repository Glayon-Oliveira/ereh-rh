package com.lmlasmo.ereh.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lmlasmo.ereh.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements UserDetails {
	
	private static final long serialVersionUID = -5904327041694099145L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;		
	
	@Column
	private boolean locked = false;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(nullable = false)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(nullable = false)
	private Position position;	
	
	public Users() {}	
	
	public Users(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.username = userDTO.getUsername();
		this.locked = userDTO.isLocked();		
	}
	
	@PrePersist
	@PreUpdate
	private void init() {		
		username = username.toLowerCase();			
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if(position != null) {
		
			String role = position.getRole().getAuthority();
			
			RoleType roleType = RoleType.valueOf(role);
			
			List<? extends GrantedAuthority> authorities = RoleType.getRolesByRole(roleType)
				.stream()
				.map(r -> new SimpleGrantedAuthority(r.name()))
				.toList();
			return authorities;
		}else {
			return List.of(new SimpleGrantedAuthority(""));
		}
			
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked();
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

}
