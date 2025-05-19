package com.hospital.HospitalManagementSystem.model;

import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.User;
import com.hospital.HospitalManagementSystem.model.user.UserImpl;

public class Admin {
	private User user;
	private String adminPassword;

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin(String userId, String userName, Address userAddress, String adminPassword) {
		super();
		this.user = new UserImpl(userId, userName, userAddress);
		this.adminPassword = adminPassword;
	}

	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", adminPassword=" + adminPassword + "]";
	}
	
	

}
