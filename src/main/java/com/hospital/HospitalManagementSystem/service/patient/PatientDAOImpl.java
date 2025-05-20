package com.hospital.HospitalManagementSystem.service.patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.User;
import com.hospital.HospitalManagementSystem.model.user.UserImpl;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAO;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAOImpl;

public class PatientDAOImpl implements PatientDAO {
	private Connection con = getConnection();
	
	private DoctorDAO doctorDao = new DoctorDAOImpl();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	@Override
	public int insert(Patient patient) {
		int affectedRows = 0;
		
		if (!patient.getUser().getUserId().substring(0, 3).equals("PTN")) {
			return 0;
		}
		
		String sql = "insert into patient (patient_id, patient_name, patient_address, disease, assigned_doctor_id) values(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, patient.getUser().getUserId());
			ps.setString(2, patient.getUser().getUserName());
			ps.setString(3, patient.getUser().getUserAddress().toString());
			ps.setString(4, patient.getDisease());
			ps.setString(5, patient.getAssignedDoctor().getUser().getUserId());
			
			affectedRows = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	@Override
	public int update(Patient patient) {
		int updatedRows = 0;
		
		String sql = "update patient set patient_name=?, patient_address=?, disease=?, assigned_doctor=? where patient_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, patient.getUser().getUserName());
			ps.setString(2, patient.getUser().getUserAddress().toString());
			ps.setString(3, patient.getDisease());
			ps.setString(4, patient.getAssignedDoctor().getUser().getUserId());
			ps.setString(5, patient.getUser().getUserId());

			updatedRows = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedRows;
	}

	@Override
	public int delete(String patientId) {
		int updatedRows = 0;
		
		String sql = "delete from patient where patient_id = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, patientId);
			
			updatedRows = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updatedRows;
	}

	@Override
	public Patient getPatient(String patientId) {
		Patient patient = new Patient();
		
		String sql = "select * from patient where patient_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, patientId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String userName = rs.getString("patient_name");
				String address = rs.getString("patient_address");
				String disease= rs.getString("disease");
				String assignedDoctorId = rs.getString("assigned_doctor_id");

				Address adr = new Address();
				String[] addParts = address.trim().split(",");
				adr.setBlockNumber(addParts[0]);
				adr.setArea(addParts[1]);
				adr.setCity(addParts[2]);
				adr.setCountry(addParts[3]);

				User user = new UserImpl(patientId, userName, adr);

				patient.setUser(user);
				patient.setDisease(disease);
				patient.setAssignedDoctor(doctorDao.getDoctor(assignedDoctorId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patient;
	}

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<>();
		
		String sql = "select * from patient";
		
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Patient patient = new Patient();
				String userId = rs.getString("patient_id");
				String userName = rs.getString("patient_name");
				String address = rs.getString("patient_address");
				String disease= rs.getString("disease");
				String assignedDoctorId = rs.getString("assigned_doctor_id");

				Address adr = new Address();
				String[] addParts = address.trim().split(",");
				adr.setBlockNumber(addParts[0]);
				adr.setArea(addParts[1]);
				adr.setCity(addParts[2]);
				adr.setCountry(addParts[3]);

				User user = new UserImpl(userId, userName, adr);

				patient.setUser(user);
				patient.setDisease(disease);
				patient.setAssignedDoctor(doctorDao.getDoctor(assignedDoctorId));
				
				patients.add(patient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patients;
	}

}
