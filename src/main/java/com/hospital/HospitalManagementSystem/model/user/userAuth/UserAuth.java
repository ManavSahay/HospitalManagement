package com.hospital.HospitalManagementSystem.model.user.userAuth;

public class UserAuth {
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuth(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public UserAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserAuth [userId=" + userId + ", password=" + password + "]";
	}

}
