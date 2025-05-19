package com.hospital.HospitalManagementSystem.model.treatment;

public class Treatment {
	int treatmentId;
	String doctor_id;
	String patient_id;
	String status;

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Treatment(int treatmentId, String doctor_id, String patient_id, String status) {
		super();
		this.treatmentId = treatmentId;
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.status = status;
	}

	public Treatment(String doctor_id, String patient_id, String status) {
		super();
		this.doctor_id = doctor_id;
		this.patient_id = patient_id;
		this.status = status;
	}

	public Treatment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Treatment [treatmentId=" + treatmentId + ", doctor_id=" + doctor_id + ", patient_id=" + patient_id
				+ ", status=" + status + "]";
	}

}
