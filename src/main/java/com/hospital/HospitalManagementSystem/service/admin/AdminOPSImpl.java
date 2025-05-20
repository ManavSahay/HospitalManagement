package com.hospital.HospitalManagementSystem.service.admin;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.treatment.Treatment;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.service.admin.userAuth.UserAuthDAO;
import com.hospital.HospitalManagementSystem.service.admin.userAuth.UserAuthDAOImpl;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAO;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAOImpl;
import com.hospital.HospitalManagementSystem.service.patient.PatientDAO;
import com.hospital.HospitalManagementSystem.service.patient.PatientDAOImpl;
import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAO;
import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAOImpl;

public class AdminOPSImpl implements AdminOPS {
	private DoctorDAO doctorDao = new DoctorDAOImpl();
	private PatientDAO patientDao = new PatientDAOImpl();
	private TreatmentDAO treatmentDao = new TreatmentDAOImpl();
	private UserAuthDAO userAuthDao = new UserAuthDAOImpl();

	@Override
	public int addDoctor(Doctor doctor, String password) {
		return doctorDao.insert(doctor) + userAuthDao.insert(new UserAuth(doctor.getUser().getUserId(), password));
	}

	@Override
	public int addPatient(Patient patient, String password) {
		return patientDao.insert(patient) + userAuthDao.insert(new UserAuth(patient.getUser().getUserId(), password))
				+ treatmentDao.insert(new Treatment(patient.getAssignedDoctor().getUser().getUserId(), patient.getUser().getUserId(), "O"));
	}

	@Override
	public int updateDoctor(Doctor doctor) {
		return doctorDao.update(doctor);
	}

	@Override
	public int updatePatient(Patient patient) {
		return patientDao.update(patient);
	}

	@Override
	public int deleteDoctor(String doctorId) {
		return patientDao.getAllPatients().stream()
				.filter(o -> o.getAssignedDoctor() != null && o.getAssignedDoctor().getUser().getUserId().equals(doctorId))
				.map(o -> patientDao.update(o.getUser().getUserId()))
				.reduce(0, (a, b) -> a + b) + treatmentDao.deleteAllTreatments(doctorId)
				+ doctorDao.delete(doctorId) + userAuthDao.delete(doctorId);
	}

	@Override
	public int deletePatient(String patientId) {
		return treatmentDao.delete(new Treatment(patientId, patientDao.getPatient(patientId).getAssignedDoctor().getUser().getUserId(), "")) + 
				patientDao.delete(patientId) + userAuthDao.delete(patientId);
	}

	@Override
	public Doctor getDoctor(String doctorId) {
		return doctorDao.getDoctor(doctorId);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.getAllDoctors();
	}

	@Override
	public Patient getPatient(String patientId) {
		return patientDao.getPatient(patientId);
	}

	@Override
	public List<Patient> getAllPatients() {
		return patientDao.getAllPatients();
	}

	@Override
	public void assignDoctor(Treatment treatment) {
		patientDao.assignDoctor(treatment);
		treatmentDao.insert(treatment);
	}

	@Override
	public List<Treatment> getTreatments(String doctorId) {
		return treatmentDao.getTreatments(doctorId);
	}

	@Override
	public List<Treatment> getAllTreatments() {
		return treatmentDao.getAllTreatments();
	}

	@Override
	public List<UserAuth> checkUsers() {
		return userAuthDao.getAllUserAuths();
	}

	@Override
	public UserAuth checkUser(String userId) {
		return userAuthDao.getUserAuth(userId);
	}

	@Override
	public boolean changeUserPassword(UserAuth userAuth) {
		return userAuthDao.update(userAuth) != 0 ? true : false;
	}

}
