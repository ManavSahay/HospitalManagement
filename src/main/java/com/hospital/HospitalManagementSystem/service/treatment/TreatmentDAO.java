package com.hospital.HospitalManagementSystem.service.treatment;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.treatment.Treatment;

public interface TreatmentDAO {
	public int insert(Treatment treatment);
	public int update(Treatment treatment);
	public int delete(Treatment treatment);
	public int deleteAllTreatments(String doctorId);
	public List<Treatment> getTreatments(String doctorId);
	public List<Treatment> getAllTreatments();
}
