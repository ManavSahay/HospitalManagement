package com.hospital.HospitalManagementSystem.service.doctor;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.Doctor;

public interface DoctorDAO {
	public int insert(Doctor doctor);
	public int update(Doctor doctor);
	public int delete(String doctorId);
	public Doctor getDoctor(String doctorId);
	public List<Doctor> getAllDoctors();
}
