package com.hospital.HospitalManagementSystem.model.user;

import com.hospital.HospitalManagementSystem.model.address.Address;

public interface User {
	public String getUserId();
	public String getUserName();
	public Address getUserAddress();
	public String toString();
}
