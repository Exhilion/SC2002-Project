package OOPProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

public class MedicalRecord {
	private String MedicalRecordID;
	private Patient Patient;
	private Diagnosis Diagnosis;
	private Treatment Treatment;
	private List<Prescription> prescriptions; // Changed from single Prescription to List

	public MedicalRecord(String MedicalRecordID, Patient Patient, Diagnosis Diagnosis, Treatment Treatment,
			List<Prescription> prescriptions) {
		this.MedicalRecordID = MedicalRecordID;
		this.Patient = Patient;
		this.Diagnosis = Diagnosis;
		this.Treatment = Treatment;
		this.prescriptions = prescriptions; // Updated constructor to accept a list of prescriptions
	}

	// Getter and Setter methods

	public String getRecordID() {
		return MedicalRecordID;
	}

	public Patient getPatient() {
		return Patient;
	}

	public void setPatient(Patient patient) {
		this.Patient = patient;
	}

	public Diagnosis getDiagnosis() {
		return Diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.Diagnosis = diagnosis;
	}

	public Treatment getTreatment() {
		return Treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.Treatment = treatment;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	// Filter medical records by hospital ID
	public static List<MedicalRecord> filterByHospitalId(List<MedicalRecord> records, String hospitalId) {
		return records.stream().filter(record -> record.getPatient().getHospitalId() != null
				&& record.getPatient().getHospitalId().equals(hospitalId)).collect(Collectors.toList());
	}


}
