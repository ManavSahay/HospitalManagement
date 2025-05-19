package com.hospital.HospitalManagementSystem.panel;

import java.util.Scanner;

import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.changepassword.ChangePassword;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAO;
import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAOImpl;

public class PatientPanel extends ChangePassword implements Execute {
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public PatientPanel(Patient patient) {
		this.patient = patient;
	}

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello Patient " + patient.getUser().getUserName());
		
		System.out.println("What would you like to do?");
		
		boolean exit = false;
		int choice = -1;
		
		do {
			System.out.println("1. View your details");
			System.out.println("2. View your doctor's details");
			System.out.println("3. Change your password");
			System.out.println("4. Logout");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
			
			sc.nextLine();
			
			if (choice == 1) {
				System.out.println("Patient's ID: " + patient.getUser().getUserId());
				System.out.println("Patient's Name: " + patient.getUser().getUserName());
				System.out.println("Patient's Address: " + patient.getUser().getUserAddress().toString());
				System.out.println("Patient's Disease: " + patient.getDisease());
				System.out.println("Patient's Assigned Doctor: " + patient.getAssignedDoctor().getUser().getUserId());
			} else if (choice == 2) {
				Doctor doctor = patient.getAssignedDoctor();
				
				System.out.println("Doctor's ID: " + doctor.getUser().getUserId());
				System.out.println("Doctor's Name: " + doctor.getUser().getUserName());
				System.out.println("Doctor's Address: " + doctor.getUser().getUserAddress().toString());
				System.out.println("Doctor's Department: " + doctor.getDepartment());
				System.out.println("Doctor's Experience: " + doctor.getExperience());
			} else if (choice == 3) {
				System.out.println("Enter your old password:");
				String oldPassword = sc.nextLine();
				
				System.out.println("Enter new password:");
				String newPassword = sc.nextLine();
				
				if(changePassword(new UserAuth(patient.getUser().getUserId(), oldPassword), newPassword)) {
					System.out.println("Password changed");
				} else {
					System.out.println("Password not changed");
				}
			} else if (choice == 4) {
				System.out.println("Logging Out");
				exit = true;
			} else {
				System.out.println("Invalid Input!");
			}
		} while (!exit);
	}

}
