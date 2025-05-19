package com.hospital.HospitalManagementSystem.model.user;

import com.hospital.HospitalManagementSystem.model.address.Address;

public class UserImpl implements User {
	private String userId;
	private String userName;
	private Address userAddress;

	@Override
	public String getUserId() {
		return this.userId;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public Address getUserAddress() {
		return this.userAddress;
	}

	public UserImpl(String userId, String userName, Address userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAddress = userAddress;
	}

	public UserImpl() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserImpl [userId=" + userId + ", userName=" + userName + ", userAddress=" + userAddress + "]";
	}
	
}
