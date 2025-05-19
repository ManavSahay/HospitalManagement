package com.hospital.HospitalManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.User;
import com.hospital.HospitalManagementSystem.model.user.UserImpl;

public class Doctor {
	private User user;
	private String department;
	private int experience;
	private List<Patient> patientsTreated;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public List<Patient> getPatientsTreated() {
		return patientsTreated;
	}

	public void setPatientsTreated(ArrayList<Patient> patientsTreated) {
		this.patientsTreated = patientsTreated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor(String userId, String userName, Address userAddress, String department, int experience) {
		super();
		this.user = new UserImpl(userId, userName, userAddress);
		this.department = department;
		this.experience = experience;
		this.patientsTreated = new ArrayList<Patient>();
	}

	public Doctor() {
		super();
		this.patientsTreated = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Doctor [user=" + user + ", department=" + department + ", experience=" + experience + "]";
	}
	
	public void addPatient(Patient patient) {
		this.patientsTreated.add(patient);
	}

}
