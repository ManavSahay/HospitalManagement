package com.hospital.HospitalManagementSystem.service.admin.userAuth;

import java.util.List;

import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;

public interface UserAuthDAO {
	public int insert(UserAuth userAuth);
	public int delete(String userId);
	public int update(UserAuth userAuth);
	public UserAuth getUserAuth(String userId);
	public List<UserAuth> getAllUserAuths();
}
