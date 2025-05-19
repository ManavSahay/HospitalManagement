package com.hospital.HospitalManagementSystem.service.doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.User;
import com.hospital.HospitalManagementSystem.model.user.UserImpl;

public class DoctorDAOImpl implements DoctorDAO {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	@Override
	public int insert(Doctor doctor) {
		int affectedRows = 0;

		String sql = "insert into doctor(doctor_id, doctor_name, doctor_address, department, experience) values(?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, doctor.getUser().getUserId());
			ps.setString(2, doctor.getUser().getUserName());
			ps.setString(3, doctor.getUser().getUserAddress().toString());
			ps.setString(4, doctor.getDepartment());
			ps.setInt(5, doctor.getExperience());

			affectedRows = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return affectedRows;
	}

	@Override
	public int update(Doctor doctor) {
		int updatedRows = 0;

		String sql = "update doctor set doctor_name=?, doctor_address=?, department=?, experience=? where doctor_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, doctor.getUser().getUserName());
			ps.setString(2, doctor.getUser().getUserAddress().toString());
			ps.setString(3, doctor.getDepartment());
			ps.setInt(4, doctor.getExperience());
			ps.setString(5, doctor.getUser().getUserId());

			updatedRows = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedRows;
	}

	@Override
	public int delete(String doctorId) {
		int updatedRows = 0;

		String sql = "delete from doctor where doctor_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, doctorId);

			updatedRows = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updatedRows;
	}

	@Override
	public Doctor getDoctor(String doctorId) {
		Doctor doctor = new Doctor();

		String sql = "select * from doctor where doctor_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, doctorId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String userName = rs.getString("doctor_name");
				String address = rs.getString("doctor_address");
				String department = rs.getString("department");
				int experience = rs.getInt("experience");

				Address adr = new Address();
				String[] addParts = address.trim().split(",");
				adr.setBlockNumber(addParts[0]);
				adr.setArea(addParts[1]);
				adr.setCity(addParts[2]);
				adr.setCountry(addParts[3]);

				User user = new UserImpl(doctorId, userName, adr);

				doctor.setUser(user);
				doctor.setDepartment(department);
				doctor.setExperience(experience);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();

		String sql = "select * from doctor";

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Doctor doctor = new Doctor();
				
				String userId = rs.getString("doctor_id");
				String userName = rs.getString("doctor_name");
				String address = rs.getString("doctor_address");
				String department = rs.getString("department");
				int experience = rs.getInt("experience");

				Address adr = new Address();
				String[] addParts = address.trim().split(",");
				adr.setBlockNumber(addParts[0]);
				adr.setArea(addParts[1]);
				adr.setCity(addParts[2]);
				adr.setCountry(addParts[3]);

				User user = new UserImpl(userId, userName, adr);

				doctor.setUser(user);
				doctor.setDepartment(department);
				doctor.setExperience(experience);
				
				doctors.add(doctor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctors;
	}
	
}
