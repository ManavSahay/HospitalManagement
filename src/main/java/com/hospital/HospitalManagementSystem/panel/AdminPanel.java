package com.hospital.HospitalManagementSystem.panel;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hospital.HospitalManagementSystem.model.Admin;
import com.hospital.HospitalManagementSystem.model.Doctor;
import com.hospital.HospitalManagementSystem.model.Patient;
import com.hospital.HospitalManagementSystem.model.address.Address;
import com.hospital.HospitalManagementSystem.model.treatment.Treatment;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.changepassword.ChangePassword;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;
import com.hospital.HospitalManagementSystem.service.admin.AdminOPS;
import com.hospital.HospitalManagementSystem.service.admin.AdminOPSImpl;

public class AdminPanel extends ChangePassword implements Execute {
	private Admin admin;
	private AdminOPS adminOps;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public AdminPanel(Admin admin) {
		this.admin = admin;
	}

	private Address getAddress(String adr) {
		Address address = new Address();
		String[] addParts = adr.split(",");
		address.setBlockNumber(addParts[0]);
		address.setArea(addParts[1]);
		address.setCity(addParts[2]);
		address.setCountry(addParts[3]);

		return address;
	}

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Hello Admin " + admin.getUser().getUserName());

		System.out.println("What would you like to do?");

		boolean exit = false;
		int choice = -1;
		adminOps = new AdminOPSImpl();

		do {
			System.out.println("1. Add Doctor");
			System.out.println("2. Add Patient");
			System.out.println("3. Update Doctor");
			System.out.println("4. Update Patient");
			System.out.println("5. Delete Doctor");
			System.out.println("6. Delete Patient");
			System.out.println("7. Search a Doctor");
			System.out.println("8. Show all Doctors");
			System.out.println("9. Search a Patient");
			System.out.println("10. Show all Patients");
			System.out.println("11. Assign Doctor");
			System.out.println("12. Show all Treatments");
			System.out.println("13. Show all Users");
			System.out.println("14. Fina a User");
			System.out.println("15. Chance any User's Password");
			System.out.println("16. Change your Password");
			System.out.println("17. Logout");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();

			sc.nextLine();

			if (choice == 1) {
				System.out.println("Enter Doctor's ID:");
				String doctorId = sc.nextLine();

				System.out.println("Enter Doctor's Name:");
				String doctorName = sc.nextLine();

				System.out.println("Enter Doctor's Address (Block Number, Area, City, Country):");
				Address address = getAddress(sc.nextLine());

				System.out.println("Enter Doctor's Department:");
				String department = sc.nextLine();
				
				System.out.println("Enter Doctor's Experience:");
				int experience = sc.nextInt();
				
				sc.nextLine();

				System.out.println("Enter the password:");
				String password = sc.nextLine();

				Doctor doctor = new Doctor(doctorId, doctorName, address, department, experience);

				int insertedRows = adminOps.addDoctor(doctor, password);

				if (insertedRows != 2) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Doctor entered successfully");
				}
			} else if (choice == 2) {
				System.out.println("Enter Patient's ID:");
				String patientId = sc.nextLine();

				System.out.println("Enter Patient's Name:");
				String patientName = sc.nextLine();

				System.out.println("Enter Patient's Address (Block Number, Area, City, Country):");
				Address address = getAddress(sc.nextLine());

				System.out.println("Enter Patient's Disease:");
				String disease = sc.nextLine();

				System.out.println("Enter Patient's Addigned Doctor ID:");
				String assignedDoctorId = sc.nextLine();

				System.out.println("Enter the password:");
				String password = sc.nextLine();

				Patient patient = new Patient(patientId, patientName, address, disease,
						adminOps.getDoctor(assignedDoctorId));

				int insertedRows = adminOps.addPatient(patient, password);

				if (insertedRows != 2) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Patient entered successfully");
				}
			} else if (choice == 3) {
				System.out.println("Enter Doctor's ID:");
				String doctorId = sc.nextLine();

				System.out.println("Enter Doctor's Name:");
				String doctorName = sc.nextLine();

				System.out.println("Enter Doctor's Address (Block Number, Area, City, Country):");
				Address address = getAddress(sc.nextLine());

				System.out.println("Enter Doctor's Department:");
				String department = sc.nextLine();

				System.out.println("Enter Doctor's Experience:");
				int experience = sc.nextInt();

				sc.nextLine();

				Doctor doctor = new Doctor(doctorId, doctorName, address, department, experience);

				int updatedRows = adminOps.updateDoctor(doctor);

				if (updatedRows != 1) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Doctor updated successfully");
				}
			} else if (choice == 4) {
				System.out.println("Enter Patient's ID:");
				String patientId = sc.nextLine();

				System.out.println("Enter Patient's Name:");
				String patientName = sc.nextLine();

				System.out.println("Enter Patient's Address (Block Number, Area, City, Country):");
				Address address = getAddress(sc.nextLine());

				System.out.println("Enter Patient's Disease:");
				String disease = sc.nextLine();

				System.out.println("Enter Patient's Addigned Doctor ID:");
				String assignedDoctorId = sc.nextLine();

				Patient patient = new Patient(patientId, patientName, address, disease,
						adminOps.getDoctor(assignedDoctorId));

				int insertedRows = adminOps.updatePatient(patient);

				if (insertedRows != 1) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Patient updated successfully");
				}
			} else if (choice == 5) {
				System.out.println("Enter the Doctor ID to be deleted:");
				String doctorId = sc.nextLine();
								
				int deletedRows = adminOps.deleteDoctor(doctorId);
				
				if (deletedRows != 2) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Doctor deleted successfully");
				}
			} else if (choice == 6) {
				System.out.println("Enter Patient ID to be deleted:");
				String patientId = sc.nextLine();
				
				int deletedRows = adminOps.deletePatient(patientId);
				
				if (deletedRows != 2) {
					System.out.println("Some error occured!");
					System.out.println("Please check the entries and try again");
				} else {
					System.out.println("Doctor deleted successfully");
				}
			} else if (choice == 7) {
				System.out.println("Enter Doctor ID to search:");
				String doctorId = sc.nextLine();
				
				Doctor doctor = adminOps.getDoctor(doctorId);
				
				if (doctor != null) {
					System.out.println("Doctor's ID: " + doctor.getUser().getUserId());
					System.out.println("Doctor's Name: " + doctor.getUser().getUserName());
					System.out.println("Doctor's Address: " + doctor.getUser().getUserAddress().toString());
					System.out.println("Doctor's Department: " + doctor.getDepartment());
					System.out.println("Doctor's Experience: " + doctor.getExperience());
					List<String> patientsTreatedId = adminOps.getTreatments(doctorId).stream()
							.map(o -> o.getPatient_id())
							.collect(Collectors.toList());
					System.out.println("Patients Treated ID: " + patientsTreatedId);
				} else {
					System.out.println("Doctor Not Found");
				}
				
			} else if (choice == 8) {
				List<Doctor> doctors = adminOps.getAllDoctors();
				
				if (doctors.isEmpty()) {
					System.out.println("No Doctors found");
					continue;
				}
				
				for (Doctor doctor: doctors) {
					System.out.println("Doctor's ID: " + doctor.getUser().getUserId());
					System.out.println("Doctor's Name: " + doctor.getUser().getUserName());
					System.out.println("Doctor's Address: " + doctor.getUser().getUserAddress().toString());
					System.out.println("Doctor's Department: " + doctor.getDepartment());
					System.out.println("Doctor's Experience: " + doctor.getExperience());
					List<String> patientsTreatedId = adminOps.getTreatments(doctor.getUser().getUserId()).stream()
							.map(o -> o.getPatient_id())
							.collect(Collectors.toList());
					System.out.println("Patients Treated ID: " + patientsTreatedId);
				}
			} else if (choice == 9) {
				System.out.println("Enter Patient ID to search:");
				String patientId = sc.nextLine();
				
				Patient patient = adminOps.getPatient(patientId);
				
				if (patient != null) {
					System.out.println("Patient's ID: " + patient.getUser().getUserId());
					System.out.println("Patient's Name: " + patient.getUser().getUserName());
					System.out.println("Patient's Address: " + patient.getUser().getUserAddress().toString());
					System.out.println("Patient's Disease: " + patient.getDisease());
					System.out.println("Patient's Assigned Doctor: " + patient.getAssignedDoctor());
				} else {
					System.out.println("Patient not found");
				}
			} else if (choice == 10) {
				List<Patient> patients = adminOps.getAllPatients();
				
				if (patients.isEmpty()) {
					System.out.println("No Patients found");
					continue;
				}
				
				for (Patient patient: patients) {
					System.out.println("Patient's ID: " + patient.getUser().getUserId());
					System.out.println("Patient's Name: " + patient.getUser().getUserName());
					System.out.println("Patient's Address: " + patient.getUser().getUserAddress().toString());
					System.out.println("Patient's Disease: " + patient.getDisease());
					System.out.println("Patient's Assigned Doctor: " + patient.getAssignedDoctor().getUser().getUserId());
				}
			} else if (choice == 11) {
				System.out.println("Enter Doctor ID:");
				String doctorId = sc.nextLine();
				
				System.out.println("Enter Patient ID:");
				String patientId = sc.nextLine();
				
				adminOps.assignDoctor(new Treatment(doctorId, patientId, "O"));
				
				System.out.println("Doctor Assigned");
			} else if (choice == 12) {
				List<Treatment> treatments = adminOps.getAllTreatments();
				
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
			} else if (choice == 13) {
				List<UserAuth> users = adminOps.checkUsers();
				
				if (users.isEmpty()) {
					System.out.println("No users available");
					continue;
				}
				
				for (UserAuth user: users) {
					System.out.println("User ID: " + user.getUserId());
					System.out.println("Password: " + user.getPassword());
				}
			} else if (choice == 14) {
				System.out.println("Enter User ID: ");
				String userId = sc.nextLine();
				
				UserAuth user = adminOps.checkUser(userId);
				
				if (user != null) {
					System.out.println("User ID: " + user.getUserId());
					System.out.println("Password: " + user.getPassword());
				} else {
					System.out.println("User does not exist");
				}
			} else if (choice == 15) {
				System.out.println("Enter User ID: ");
				String userId = sc.nextLine();
				
				System.out.println("Enter New Password");
				String password = sc.nextLine();
				
				adminOps.changeUserPassword(new UserAuth(userId, password));
				
				System.out.println(userId + "'s password changed");
			} else if (choice == 16) {
				System.out.println("Enter your new password:");
				String password = sc.nextLine();
				
				UserAuth userAuth = new UserAuth(this.admin.getUser().getUserId(), this.admin.getAdminPassword());
				
				if (changePassword(userAuth, password)) {
					System.out.println("Password changed");
				} else {
					System.out.println("Password not changed");
				}
			} else if (choice == 17) {
				System.out.println("Logging Out");
				exit = true;
			} else {
				System.out.println("Invalid Input!");
			}
		} while (!exit);
	}

}
