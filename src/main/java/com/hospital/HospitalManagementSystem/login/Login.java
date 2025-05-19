package com.hospital.HospitalManagementSystem.login;

import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;

public interface Login {
	public Execute login(UserAuth userAuth);
}
