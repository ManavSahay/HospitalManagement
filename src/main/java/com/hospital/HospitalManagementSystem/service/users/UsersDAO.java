package com.hospital.HospitalManagementSystem.service.users;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.user.User;

public interface UsersDAO {
	public int insert(User user, String password);
	public int delete(String userId);
	public int update(User user, String password);
	public User getUser(String userId);
	public List<User> getAllUsers();
}
