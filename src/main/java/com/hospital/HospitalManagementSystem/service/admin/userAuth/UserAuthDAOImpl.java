package com.hospital.HospitalManagementSystem.service.admin.userAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospital.HospitalManagementSystem.connection.GetConnection;
import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;

public class UserAuthDAOImpl implements UserAuthDAO {
	private Connection con = getConnection();

	private Connection getConnection() {
		GetConnection getConn = new GetConnectionImpl();
		return getConn.getConnection();
	}

	@Override
	public int insert(UserAuth userAuth) {
		int affectedRows = 0;
		
		String sql = "insert into users(user_id, user_password) values(?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userAuth.getUserId());
			ps.setString(2, userAuth.getPassword());
			
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
	public int update(UserAuth userAuth) {
		int updatedRows = 0;
		
		String sql = "update users set user_password=? where user_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userAuth.getPassword());
			ps.setString(2, userAuth.getUserId());
			
			updatedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedRows;
	}

	@Override
	public UserAuth getUserAuth(String userId) {
		UserAuth userAuth = null;
		
		String sql = "select * from users where user_id=?";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String userAuthId = rs.getString(1);
				String password = rs.getString(2);
				
				userAuth = new UserAuth(userAuthId, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userAuth;
	}

	@Override
	public List<UserAuth> getAllUserAuths() {
		List<UserAuth> userAuths = new ArrayList<UserAuth>();
		
		String sql = "select * from users";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String userAuthId = rs.getString(1);
				String password = rs.getString(2);
				
				userAuths.add(new UserAuth(userAuthId, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userAuths;
	}
	
	
}
