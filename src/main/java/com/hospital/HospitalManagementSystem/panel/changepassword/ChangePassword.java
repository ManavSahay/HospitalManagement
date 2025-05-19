package com.hospital.HospitalManagementSystem.panel.changepassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;

public class ChangePassword {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}
	
	public boolean changePassword(UserAuth userAuth, String newPassword) {		
		String sql = "update users set user_password=? where user_id=? and user_password=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setString(2, userAuth.getUserId());
			ps.setString(3, userAuth.getPassword());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
}
