package com.nn.vehicles.model;

public enum Role {
	EMPLOYEE("EMPLOYEE"), ADMIN("ADMIN"), CUSTOMER("CUSTOMER"),  ;

	
	private String userType;

	Role(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return userType;
	}
}
