package com.hospital.HospitalManagementSystem.service.treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.treatment.Treatment;

public class TreatmentDAOImpl implements TreatmentDAO {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	@Override
	public int insert(Treatment treatment) {
		int affectedRows = 0;
		
		String sql = "insert into treatment(doctor_id, patient_id, treatment_status) values(?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, treatment.getDoctor_id());
			ps.setString(2, treatment.getPatient_id());
			ps.setString(3, treatment.getStatus());
			
			affectedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	@Override
	public int update(Treatment treatment) {
		int updatedRows = 0;
		
		// Treatment status is 'O' -> Ongoing when the treatment is in progress and 'D' -> Done when the treatment is completer.
		String sql = "update treatment set treatment_status='D' where patient_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, treatment.getPatient_id());
			
			updatedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedRows;
	}

	@Override
	public List<Treatment> getTreatments(String doctorId) {
		List<Treatment> treatments = new ArrayList<Treatment>();
		
		String sql = "select * from treatment where doctor_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, doctorId);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int treatmentId = rs.getInt(1);
				String docId = rs.getString(2);
				String patientId = rs.getString(3);
				String status = rs.getString(4);
				
				Treatment treatment = new Treatment(treatmentId, docId, patientId, status);
				
				treatments.add(treatment);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return treatments;
	}

	@Override
	public List<Treatment> getAllTreatments() {
		List<Treatment> treatments = new ArrayList<Treatment>();
		
		String sql = "select * from treatment";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int treatmentId = rs.getInt(1);
				String docId = rs.getString(2);
				String patientId = rs.getString(3);
				String status = rs.getString(4);
				
				Treatment treatment = new Treatment(treatmentId, docId, patientId, status);
				
				treatments.add(treatment);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return treatments;
	}

}
