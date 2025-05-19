package com.hospital.HospitalManagementSystem.service.patient;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.Patient;

public interface PatientDAO {
	public int insert(Patient patient);
	public int update(Patient patient);
	public int delete(String patientId);
	public Patient getPatient(String patientId);
	public List<Patient> getAllPatients();
}
