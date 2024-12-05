package com.lmlasmo.ereh.model;

import java.util.Arrays;
import java.util.List;

public enum RoleType {

	COMUM_USER(1),
	GESTOR_USER(2),
	SUBADMIN_USER(3),
	ADMIN_USER(4);
	
	private int weight;
	
	RoleType(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public static List<RoleType> getRolesByRole(RoleType role) {
		
			List<RoleType> roles = Arrays.asList(values())
					.stream()
					.filter(r -> r.getWeight()<role.getWeight())
					.toList();
			
			roles.add(role);
			
			return roles;
		
	}
	
}
