package com.hospital.HospitalManagementSystem.test;

//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.hospital.HospitalManagementSystem.connection.GetConnection;
//import com.hospital.HospitalManagementSystem.connection.GetConnectionImpl;
//import com.hospital.HospitalManagementSystem.login.LoginImpl;
//import com.hospital.HospitalManagementSystem.model.Admin;
//import com.hospital.HospitalManagementSystem.model.Doctor;
//import com.hospital.HospitalManagementSystem.model.Patient;
//import com.hospital.HospitalManagementSystem.model.address.Address;
//import com.hospital.HospitalManagementSystem.model.treatment.Treatment;
//import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
//import com.hospital.HospitalManagementSystem.panel.AdminPanel;
//import com.hospital.HospitalManagementSystem.panel.DoctorPanel;
//import com.hospital.HospitalManagementSystem.panel.PatientPanel;
//import com.hospital.HospitalManagementSystem.service.admin.AdminOPS;
//import com.hospital.HospitalManagementSystem.service.admin.AdminOPSImpl;
//import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAO;
//import com.hospital.HospitalManagementSystem.service.doctor.DoctorDAOImpl;
//import com.hospital.HospitalManagementSystem.service.patient.PatientDAO;
//import com.hospital.HospitalManagementSystem.service.patient.PatientDAOImpl;
//import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAO;
//import com.hospital.HospitalManagementSystem.service.treatment.TreatmentDAOImpl;

public class Test {
	public static void main(String[] args) throws Exception {
//		String userId = "ADM_001";
//		String userName = "Adam";
//		Address userAddress = new Address("B-01", "Siruseri", "Chennai", "India");
		
//		DoctorDAO doctorDao = new DoctorDAOImpl();
//		PatientDAO patientDao = new PatientDAOImpl();
//		TreatmentDAO treatmentDao = new TreatmentDAOImpl();
//		AdminOPS adminOps = new AdminOPSImpl();
		
//		LoginImpl login = new LoginImpl();

//		
//		Admin admin = new Admin(userId, userName, userAddress, "Pass");
//		
//		Doctor doctor = new Doctor("DOC_001", "Doctor", userAddress, "Dental", 10);
//		Doctor doctor2 = new Doctor("DOC_002", "Doctor2", userAddress, "Dental", 10);
//		Doctor doctor3 = new Doctor("DOC_003", "Doctor3", userAddress, "Dental", 10);
////		
//		Patient patient1 = new Patient("PTN_001", "Patient", userAddress, "Teeth germs", doctor);
//		Patient patient2 = new Patient("PTN_002", "Patient2", userAddress, "Liver Failure", doctor2);
//		Patient patient3 = new Patient("PTN_003", "Patient3", userAddress, "Liver Failure", doctor3);
//		
//		Treatment treatment = new Treatment("DOC_001", "PTN_001", "O");
//		Treatment treatment2 = new Treatment("DOC_002", "PTN_003", "O");
//		Treatment treatment3 = new Treatment("DOC_001", "PTN_002", "O");
		
//		
//		doctor.addPatient(patient1);
//		doctor.addPatient(patient2);
//		
//		System.out.println(admin);
//		System.out.println(doctor);
//		System.out.println(patient1);
//		System.out.println(patient2);
		
//		GetConnection getConnection = new GetConnectionImpl();
//		Connection con = getConnection.getConnection();
//		try {
//			DatabaseMetaData metaData = con.getMetaData();
//			System.out.println(metaData.getDatabaseProductName());
//			System.out.println(metaData.getURL());
//			System.out.println(metaData.getUserName());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		doctorDao.insert(doctor);
//		doctorDao.insert(doctor2);
//		doctorDao.insert(doctor3);
		
//		doctorDao.update(new Doctor("DOC_001", "Doctor2", userAddress, "Dental", 10));
		
//		doctorDao.delete("DOC_001");
		
//		Doctor doc= doctorDao.getDoctor("DOC_001");
//		System.out.println(doc);

//		List<Doctor> doctors = doctorDao.getAllDoctors();

//		for (Doctor doc: doctors) {
//			System.out.println(doc);
//		}
		
		
//		patientDao.insert(patient1);
//		patientDao.insert(patient2);
//		patientDao.insert(patient3);
		
//		treatmentDao.insert(treatment);
//		treatmentDao.insert(treatment2);
//		treatmentDao.insert(treatment3);
//		
//		treatment.setTreatmentId(101);
//		treatmentDao.update(treatment);

//		List<Treatment> treatments = treatmentDao.getAllTreatments();
//		
//		for (Treatment treatmens: treatments) {
//			System.out.println(treatmens);
//		}
		
//		adminOps.addDoctor(doctor, "doc_pass");
//		adminOps.addPatient(patient3, "ptn_pass");
		
//		PatientPanel object = (PatientPanel) login.login(new UserAuth("PTN_003", "ptn_pass"));
//		System.out.println(object.getPatient());
	}
}
