package com.hospital.HospitalManagementSystem.service.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.user.User;

public class UsersDAOImpl implements UsersDAO {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	@Override
	public int insert(User user, String password) {
		int affectedRows = 0;
		
		String sql = "insert into users (user_id, user_password) values(?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, password);
			
			affectedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	@Override
	public int delete(String userId) {
		int deletedRows = 0;
		
		String sql = "delete from users where user_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			
			deletedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deletedRows;
	}

	@Override
	public int update(User user, String password) {
		int updatedRows = 0;
		
		String sql = "update users set password=? where user_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, user.getUserId());
			
			updatedRows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedRows;
	}

	@Override
	public User getUser(String userId) {
		User user = null;
		
		String sql = "select * from users where user_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
