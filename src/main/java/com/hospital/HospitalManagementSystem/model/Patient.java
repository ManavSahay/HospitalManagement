package com.hospital.HospitalManagementSystem.model;

import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.User;
import com.hospital.HospitalManagementSystem.model.user.UserImpl;

public class Patient {
	private User user;
	private String disease;
	private Doctor assignedDoctor;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Doctor getAssignedDoctor() {
		return assignedDoctor;
	}

	public void setAssignedDoctor(Doctor assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}

	public Patient(String userId, String userName, Address userAddress, String disease, Doctor assignedDoctor) {
		super();
		this.user = new UserImpl(userId, userName, userAddress);
		this.disease = disease;
		this.assignedDoctor = assignedDoctor;
	}

	public Patient() {
		super();
	}

	@Override
	public String toString() {
		return "Patient [user=" + user + ", disease=" + disease + ", assignedDoctor=" + assignedDoctor.getUser().getUserId() + "]";
	}
	
	

}
