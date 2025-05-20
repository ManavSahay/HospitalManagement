package com.hospital.HospitalManagementSystem.panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.treatment.Treatment;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.changepassword.ChangePassword;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;
import com.hospital.HospitalManagementSystem.service.patient.PatientDAO;
import com.hospital.HospitalManagementSystem.service.patient.PatientDAOImpl;
import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAO;
import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAOImpl;

public class DoctorPanel extends ChangePassword implements Execute {
	private Scanner sc = new Scanner(System.in);
	private Doctor doctor;
	private TreatmentDAO treatmentDao;
	private PatientDAO patientDao;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public DoctorPanel(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public void execute() {		
		System.out.println("Hello Doctor " + doctor.getUser().getUserName());
		
		System.out.println("What would you like to do?");
		
		boolean exit = false;
		int choice = -1;
		treatmentDao = new TreatmentDAOImpl();
		patientDao = new PatientDAOImpl();
		
		
		do {
			System.out.println("1. View your details");
			System.out.println("2. View your patient's details");
			System.out.println("3. View yout treatment details");
			System.out.println("4. Complete a treatment");
			System.out.println("5. Change your password");
			System.out.println("6. Logout");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
			
			sc.nextLine();
			
			if (choice == 1) {
				System.out.println("Doctor's ID: " + doctor.getUser().getUserId());
				System.out.println("Doctor's Name: " + doctor.getUser().getUserName());
				System.out.println("Doctor's Address: " + doctor.getUser().getUserAddress().toString());
				System.out.println("Doctor's Department: " + doctor.getDepartment());
				System.out.println("Doctor's Experience: " + doctor.getExperience());
				List<String> patientsTreatedId = treatmentDao.getTreatments(doctor.getUser().getUserId()).stream()
						.map(o -> o.getPatient_id())
						.collect(Collectors.toList());
				System.out.println("Patients Treated ID: " + patientsTreatedId);
			} else if (choice == 2) {
				List<String> patientsTreatedId = treatmentDao.getTreatments(doctor.getUser().getUserId()).stream()
						.map(o -> o.getPatient_id())
						.collect(Collectors.toList());
				
				List<Patient> patients = new ArrayList<>();
				
				for (String patientId: patientsTreatedId) {
					patients.add(patientDao.getPatient(patientId));
				}
				
				for (Patient patient: patients) {
					System.out.println("Patient's ID: " + patient.getUser().getUserId());
					System.out.println("Patient's Name: " + patient.getUser().getUserName());
					System.out.println("Patient's Address: " + patient.getUser().getUserAddress().toString());
					System.out.println("Patient's Disease: " + patient.getDisease());
				}
			} else if (choice == 3) {
				List<Treatment> treatments = treatmentDao.getTreatments(doctor.getUser().getUserId());
				
				if (treatments.isEmpty()) {
					System.out.println("No treatments available");
					continue;
				}
				
				for (Treatment treatment: treatments) {
					System.out.println("Treatment ID: " + treatment.getTreatmentId());
					System.out.println("Doctor ID: " + treatment.getDoctor_id());
					System.out.println("Patient ID: " + treatment.getPatient_id());
					System.out.println("Treatment Status: " + treatment.getStatus());
				}
			} else if (choice == 4) {
				System.out.println("Enter the patient ID:");
				String patientId = sc.nextLine();
				treatmentDao.update(new Treatment(doctor.getUser().getUserId(), patientId, "D"));
			} else if (choice == 5) {
				System.out.println("Enter your old password:");
				String oldPassword = sc.nextLine();
				
				System.out.println("Enter new password:");
				String newPassword = sc.nextLine();
				
				if(changePassword(new UserAuth(doctor.getUser().getUserId(), oldPassword), newPassword)) {
					System.out.println("Password changed");
				} else {
					System.out.println("Password not changed");
				}
			} else if (choice == 6) {
				System.out.println("Logging Out");
				exit = true;
			} else {
				System.out.println("Invalid Input!");
			}
		} while (!exit);
		
	}
}
