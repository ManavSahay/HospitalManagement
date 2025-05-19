package com.hospital.HospitalManagementSystem.service.admin;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.treatment.Treatment;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;

public interface AdminOPS {
	public int addDoctor(Doctor doctor, String password);
	public int addPatient(Patient patient, String password);
	
	public int updateDoctor(Doctor doctor);
	public int updatePatient(Patient patient);
	
	public int deleteDoctor(String doctorId);
	public int deletePatient(String patientId);
	
	public Doctor getDoctor(String doctorId);
	public List<Doctor> getAllDoctors();
	public Patient getPatient(String patientId);
	public List<Patient> getAllPatients();
	
	public void assignDoctor(Treatment treatment);
	public List<Treatment> getAllTreatments();
	public List<Treatment> getTreatments(String doctorId);
	
	public List<UserAuth> checkUsers();
	public UserAuth checkUser(String userId);
	public boolean changeUserPassword(UserAuth userAuth);
}
