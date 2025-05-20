package com.hospital.HospitalManagementSystem.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.Admin;
import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.AdminPanel;
import com.hospital.HospitalManagementSystem.panel.DoctorPanel;
import com.hospital.HospitalManagementSystem.panel.PatientPanel;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAO;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAOImpl;

public class LoginImpl implements Login {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	private AdminPanel adminLogin(Admin admin) {
		return new AdminPanel(admin);
	}

	private DoctorPanel doctorLogin(Doctor doctor) {
		return new DoctorPanel(doctor);
	}

	private PatientPanel patientLogin(Patient patient) {
		return new PatientPanel(patient);
	}
	
	private Address setAddress(String adr) {
		Address address = new Address();
		
		String[] addParts = adr.trim().split(",");
		address.setBlockNumber(addParts[0]);
		address.setArea(addParts[1]);
		address.setCity(addParts[2]);
		address.setCountry(addParts[3]);
		
		return address;
	}

	public Execute login(UserAuth userAuth) {
		if (userAuth.getUserId().length() < 3) {
			System.out.println("Invalid User ID");
			return null;
		}

		String type = userAuth.getUserId().substring(0, 3);

		String sql = "select * from users where user_id=? and user_password=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userAuth.getUserId());
			ps.setString(2, userAuth.getPassword());

			ResultSet rs = ps.executeQuery();
			
			if (!rs.next()) {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}

		if (type.equals("ADM")) {
			sql = "select * from admins where admin_id=?";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, userAuth.getUserId());

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					Address adr = setAddress(rs.getString("admin_address"));

					return adminLogin(new Admin(rs.getString("admin_id"), rs.getString("admin_name"), adr,
							userAuth.getPassword()));
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		} else if (type.equals("DOC")) {
			sql = "select * from doctor where doctor_id=?";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, userAuth.getUserId());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {

					Address adr = setAddress(rs.getString("doctor_address"));

					return doctorLogin(new Doctor(rs.getString("doctor_id"), rs.getString("doctor_name"), adr,
							rs.getString("department"), rs.getInt("experience")));

				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (type.equals("PTN")) {
			sql = "select * from patient where patient_id=?";
			DoctorDAO doctorDao = new DoctorDAOImpl();

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, userAuth.getUserId());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					Address adr = setAddress(rs.getString("patient_address"));

					return patientLogin(new Patient(rs.getString("patient_id"), rs.getString("patient_name"), adr,
							rs.getString("disease"), doctorDao.getDoctor(rs.getString("assigned_doctor_id"))));

				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
